package org.jkiss.dbeaver.core;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBeaverActivatorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBeaverActivator}
   *   <li>{@link DBeaverActivator#getInstance()}
   *   <li>{@link DBeaverActivator#getPreferences()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBeaverActivator.<init>()",
    "DBeaverActivator DBeaverActivator.getInstance()",
    "org.jkiss.dbeaver.model.preferences.DBPPreferenceStore DBeaverActivator.getPreferences()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBeaverActivator actualDBeaverActivator = new DBeaverActivator();
    DBeaverActivator actualInstance = actualDBeaverActivator.getInstance();

    // Assert
    assertNull(actualInstance);
    assertNull(actualDBeaverActivator.getPreferences());
  }

  /**
   * Test {@link DBeaverActivator#getImageDescriptor(String)}.
   *
   * <p>Method under test: {@link DBeaverActivator#getImageDescriptor(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.eclipse.jface.resource.ImageDescriptor DBeaverActivator.getImageDescriptor(String)"
  })
  public void testGetImageDescriptor() {
    // Arrange, Act and Assert
    assertNull(DBeaverActivator.getImageDescriptor("Path"));
  }
}
