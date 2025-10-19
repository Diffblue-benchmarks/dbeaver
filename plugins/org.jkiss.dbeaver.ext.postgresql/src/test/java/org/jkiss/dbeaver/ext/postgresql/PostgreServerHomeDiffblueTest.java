package org.jkiss.dbeaver.ext.postgresql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreServerHomeDiffblueTest {
  /**
   * Test {@link PostgreServerHome#PostgreServerHome(String, String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Path Name is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreServerHome#PostgreServerHome(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgreServerHome.<init>(String, String, String)"})
  public void testNewPostgreServerHome_whenNull_thenReturnPathNameIs42() {
    // Arrange and Act
    PostgreServerHome actualPostgreServerHome =
        new PostgreServerHome("42", null, PostgreConstants.PG_INSTALL_PROP_BRANDING);

    // Assert
    File path = actualPostgreServerHome.getPath();
    assertEquals("42", path.getName());
    assertEquals("42", actualPostgreServerHome.getName());
    assertFalse(path.isAbsolute());
    assertEquals(
        PostgreConstants.PG_INSTALL_PROP_BRANDING, actualPostgreServerHome.getDisplayName());
  }

  /**
   * Test {@link PostgreServerHome#PostgreServerHome(String, String, String)}.
   *
   * <ul>
   *   <li>When {@code Path}.
   *   <li>Then return Path Name is {@code Path}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreServerHome#PostgreServerHome(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgreServerHome.<init>(String, String, String)"})
  public void testNewPostgreServerHome_whenPath_thenReturnPathNameIsPath() {
    // Arrange and Act
    PostgreServerHome actualPostgreServerHome =
        new PostgreServerHome("42", "Path", PostgreConstants.PG_INSTALL_PROP_BRANDING);

    // Assert
    assertEquals("42", actualPostgreServerHome.getName());
    File path = actualPostgreServerHome.getPath();
    assertEquals("Path", path.getName());
    assertFalse(path.isAbsolute());
    assertEquals(
        PostgreConstants.PG_INSTALL_PROP_BRANDING, actualPostgreServerHome.getDisplayName());
  }
}
