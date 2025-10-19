package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.rm.RMEvent.Action;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RMEventDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMEvent#RMEvent(Action, RMProject, String, Map)}
   *   <li>{@link RMEvent#getAction()}
   *   <li>{@link RMEvent#getProject()}
   *   <li>{@link RMEvent#getResourcePath()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMEvent.<init>(Action, RMProject, String, Map)",
    "Action RMEvent.getAction()",
    "RMProject RMEvent.getProject()",
    "String RMEvent.getResourcePath()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RMProject project = RMUtils.createAnonymousProject();

    // Act
    RMEvent actualRmEvent =
        new RMEvent(Action.RESOURCE_DELETE, project, "Resource Path", new HashMap<>());
    Action actualAction = actualRmEvent.getAction();
    RMProject actualProject = actualRmEvent.getProject();

    // Assert
    assertEquals("Resource Path", actualRmEvent.getResourcePath());
    assertEquals(Action.RESOURCE_DELETE, actualAction);
    assertSame(project, actualProject);
  }

  /**
   * Test {@link RMEvent#RMEvent(Action, RMProject)}.
   *
   * <p>Method under test: {@link RMEvent#RMEvent(Action, RMProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMEvent.<init>(Action, RMProject)"})
  public void testNewRMEvent() {
    // Arrange
    RMProject project = RMUtils.createAnonymousProject();

    // Act
    RMEvent actualRmEvent = new RMEvent(Action.RESOURCE_DELETE, project);

    // Assert
    assertNull(actualRmEvent.getResourcePath());
    assertEquals(Action.RESOURCE_DELETE, actualRmEvent.getAction());
    assertTrue(actualRmEvent.getParameters().isEmpty());
    assertSame(project, actualRmEvent.getProject());
  }

  /**
   * Test {@link RMEvent#RMEvent(Action, RMProject, String)}.
   *
   * <p>Method under test: {@link RMEvent#RMEvent(Action, RMProject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMEvent.<init>(Action, RMProject, String)"})
  public void testNewRMEvent2() {
    // Arrange
    RMProject project = RMUtils.createAnonymousProject();

    // Act
    RMEvent actualRmEvent = new RMEvent(Action.RESOURCE_DELETE, project, "Resource Path");

    // Assert
    assertEquals("Resource Path", actualRmEvent.getResourcePath());
    assertEquals(Action.RESOURCE_DELETE, actualRmEvent.getAction());
    assertTrue(actualRmEvent.getParameters().isEmpty());
    assertSame(project, actualRmEvent.getProject());
  }

  /**
   * Test {@link RMEvent#getParameters()}.
   *
   * <p>Method under test: {@link RMEvent#getParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map RMEvent.getParameters()"})
  public void testGetParameters() {
    // Arrange, Act and Assert
    assertTrue(
        new RMEvent(Action.RESOURCE_DELETE, RMUtils.createAnonymousProject())
            .getParameters()
            .isEmpty());
  }
}
