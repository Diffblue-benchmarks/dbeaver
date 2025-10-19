package org.jkiss.dbeaver.model.rcp;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBeaverNatureDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBeaverNature}
   *   <li>{@link DBeaverNature#setProject(IProject)}
   *   <li>{@link DBeaverNature#configure()}
   *   <li>{@link DBeaverNature#deconfigure()}
   *   <li>{@link DBeaverNature#getProject()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBeaverNature.<init>()",
    "void DBeaverNature.configure()",
    "void DBeaverNature.deconfigure()",
    "IProject DBeaverNature.getProject()",
    "void DBeaverNature.setProject(IProject)"
  })
  public void testGettersAndSetters() throws CoreException {
    // Arrange and Act
    DBeaverNature actualDBeaverNature = new DBeaverNature();
    actualDBeaverNature.setProject(null);
    actualDBeaverNature.configure();
    actualDBeaverNature.deconfigure();

    // Assert
    assertNull(actualDBeaverNature.getProject());
  }
}
