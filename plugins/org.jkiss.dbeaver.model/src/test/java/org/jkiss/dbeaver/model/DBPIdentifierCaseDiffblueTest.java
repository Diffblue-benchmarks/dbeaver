package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPIdentifierCaseDiffblueTest {
  /**
   * Test {@link DBPIdentifierCase#capitalizeCaseName(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBPIdentifierCase#capitalizeCaseName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPIdentifierCase.capitalizeCaseName(String)"})
  public void testCapitalizeCaseName_whenEmptyString_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", DBPIdentifierCase.capitalizeCaseName(""));
  }

  /**
   * Test {@link DBPIdentifierCase#capitalizeCaseName(String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBPIdentifierCase#capitalizeCaseName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBPIdentifierCase.capitalizeCaseName(String)"})
  public void testCapitalizeCaseName_whenName_thenReturnName() {
    // Arrange, Act and Assert
    assertEquals("Name", DBPIdentifierCase.capitalizeCaseName("Name"));
  }
}
