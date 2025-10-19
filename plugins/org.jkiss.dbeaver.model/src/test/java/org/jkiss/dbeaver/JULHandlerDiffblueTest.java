package org.jkiss.dbeaver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.logging.Level;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JULHandlerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JULHandler#JULHandler(Log)}
   *   <li>{@link JULHandler#close()}
   *   <li>{@link JULHandler#flush()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JULHandler.<init>(Log)",
    "void JULHandler.close()",
    "void JULHandler.flush()"
  })
  public void testGettersAndSetters() throws SecurityException {
    // Arrange
    Class<Object> forClass = Object.class;

    // Act
    JULHandler actualJulHandler = new JULHandler(Log.getLog(forClass));
    actualJulHandler.close();
    actualJulHandler.flush();

    // Assert
    Level level = actualJulHandler.getLevel();
    assertEquals("ALL", level.getLocalizedName());
    assertEquals("ALL", level.getName());
    assertEquals("ALL", level.toString());
    assertEquals("sun.util.logging.resources.logging", level.getResourceBundleName());
    assertNull(actualJulHandler.getEncoding());
    assertNull(actualJulHandler.getFilter());
    assertNull(actualJulHandler.getFormatter());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JULHandler#JULHandler(Log)}
   *   <li>{@link JULHandler#close()}
   *   <li>{@link JULHandler#flush()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JULHandler.<init>(Log)",
    "void JULHandler.close()",
    "void JULHandler.flush()"
  })
  public void testGettersAndSetters2() throws SecurityException {
    // Arrange
    Class<Object> forClass = Object.class;

    // Act
    JULHandler actualJulHandler = new JULHandler(Log.getLog(forClass));
    actualJulHandler.close();
    actualJulHandler.flush();

    // Assert
    Level level = actualJulHandler.getLevel();
    assertEquals("ALL", level.getLocalizedName());
    assertEquals("ALL", level.getName());
    assertEquals("ALL", level.toString());
    assertEquals("sun.util.logging.resources.logging", level.getResourceBundleName());
    assertNull(actualJulHandler.getEncoding());
    assertNull(actualJulHandler.getFilter());
    assertNull(actualJulHandler.getFormatter());
  }
}
