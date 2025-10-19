package org.jkiss.dbeaver.model.cli;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.cli.ApplicationInstanceServer.InstanceConnectionParameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ApplicationInstanceServerDiffblueTest {
  /**
   * Test InstanceConnectionParameters getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link InstanceConnectionParameters}
   *   <li>{@link InstanceConnectionParameters#isCreateNewConnection()}
   *   <li>{@link InstanceConnectionParameters#isMakeConnect()}
   *   <li>{@link InstanceConnectionParameters#isOpenConsole()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void InstanceConnectionParameters.<init>()",
    "boolean InstanceConnectionParameters.isCreateNewConnection()",
    "boolean InstanceConnectionParameters.isMakeConnect()",
    "boolean InstanceConnectionParameters.isOpenConsole()"
  })
  public void testInstanceConnectionParametersGettersAndSetters() {
    // Arrange and Act
    InstanceConnectionParameters actualInstanceConnectionParameters =
        new InstanceConnectionParameters();
    boolean actualIsCreateNewConnectionResult =
        actualInstanceConnectionParameters.isCreateNewConnection();
    boolean actualIsMakeConnectResult = actualInstanceConnectionParameters.isMakeConnect();

    // Assert
    assertFalse(actualInstanceConnectionParameters.isOpenConsole());
    assertTrue(actualIsCreateNewConnectionResult);
    assertTrue(actualIsMakeConnectResult);
  }
}
