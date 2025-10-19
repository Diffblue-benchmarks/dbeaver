package org.jkiss.dbeaver.model.impl.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.runtime.ui.DBPPlatformUI;
import org.jkiss.dbeaver.runtime.ui.console.ConsoleUserInterface;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ApplicationWorkbenchImplDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ApplicationWorkbenchImpl}
   *   <li>{@link ApplicationWorkbenchImpl#getPlatform()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ApplicationWorkbenchImpl.<init>()",
    "org.jkiss.dbeaver.model.app.DBPPlatform ApplicationWorkbenchImpl.getPlatform()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new ApplicationWorkbenchImpl().getPlatform());
  }

  /**
   * Test {@link ApplicationWorkbenchImpl#getPlatformUI()}.
   *
   * <p>Method under test: {@link ApplicationWorkbenchImpl#getPlatformUI()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPlatformUI ApplicationWorkbenchImpl.getPlatformUI()"})
  public void testGetPlatformUI() {
    // Arrange and Act
    DBPPlatformUI actualPlatformUI = new ApplicationWorkbenchImpl().getPlatformUI();

    // Assert
    assertTrue(actualPlatformUI instanceof ConsoleUserInterface);
    assertFalse(actualPlatformUI.readAndDispatchEvents());
  }
}
