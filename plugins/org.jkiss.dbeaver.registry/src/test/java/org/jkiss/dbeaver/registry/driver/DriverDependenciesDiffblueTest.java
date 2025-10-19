package org.jkiss.dbeaver.registry.driver;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.model.connection.DBPDriverDependencies;
import org.jkiss.dbeaver.model.connection.DBPDriverDependencies.DependencyNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DriverDependenciesDiffblueTest {
  /**
   * Test {@link DriverDependencies#DriverDependencies(Collection)}.
   *
   * <p>Method under test: {@link DriverDependencies#DriverDependencies(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DriverDependencies.<init>(Collection)"})
  public void testNewDriverDependencies() {
    // Arrange and Act
    DriverDependencies actualDriverDependencies = new DriverDependencies(new ArrayList<>());

    // Assert
    assertTrue(actualDriverDependencies.getLibraryList().isEmpty());
    assertTrue(actualDriverDependencies.getLibraryMap().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DriverDependencies#getLibraryList()}
   *   <li>{@link DriverDependencies#getLibraryMap()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DriverDependencies.getLibraryList()",
    "List DriverDependencies.getLibraryMap()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DriverDependencies driverDependencies = new DriverDependencies(new ArrayList<>());

    // Act
    List<DependencyNode> actualLibraryList = driverDependencies.getLibraryList();
    List<DependencyNode> actualLibraryMap = driverDependencies.getLibraryMap();

    // Assert
    assertTrue(actualLibraryList.isEmpty());
    assertTrue(actualLibraryMap.isEmpty());
  }
}
