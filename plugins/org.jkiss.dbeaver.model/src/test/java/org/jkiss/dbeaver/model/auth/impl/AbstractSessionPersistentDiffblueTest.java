package org.jkiss.dbeaver.model.auth.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.impl.app.LocalWorkspaceSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractSessionPersistentDiffblueTest {
  /**
   * Test {@link AbstractSessionPersistent#getAttributes()}.
   *
   * <p>Method under test: {@link AbstractSessionPersistent#getAttributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AbstractSessionPersistent.getAttributes()"})
  public void testGetAttributes() {
    // Arrange, Act and Assert
    assertTrue(new LocalWorkspaceSession(mock(DBPWorkspace.class)).getAttributes().isEmpty());
  }

  /**
   * Test {@link AbstractSessionPersistent#getAttribute(String)}.
   *
   * <p>Method under test: {@link AbstractSessionPersistent#getAttribute(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractSessionPersistent.getAttribute(String)"})
  public void testGetAttribute() {
    // Arrange, Act and Assert
    assertNull(new LocalWorkspaceSession(mock(DBPWorkspace.class)).getAttribute("Name"));
  }

  /**
   * Test {@link AbstractSessionPersistent#setAttribute(String, Object)}.
   *
   * <p>Method under test: {@link AbstractSessionPersistent#setAttribute(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractSessionPersistent.setAttribute(String, Object)"})
  public void testSetAttribute() {
    // Arrange
    LocalWorkspaceSession localWorkspaceSession =
        new LocalWorkspaceSession(mock(DBPWorkspace.class));
    Object object = DBPEvent.RENAME;

    // Act
    localWorkspaceSession.setAttribute("Name", object);

    // Assert
    Map<String, Object> attributes = localWorkspaceSession.getAttributes();
    assertEquals(1, attributes.size());
    Map<String, Object> stringObjectMap = localWorkspaceSession.attributes;
    assertEquals(1, stringObjectMap.size());
    assertSame(object, attributes.get("Name"));
    assertSame(object, stringObjectMap.get("Name"));
  }

  /**
   * Test {@link AbstractSessionPersistent#removeAttribute(String)}.
   *
   * <p>Method under test: {@link AbstractSessionPersistent#removeAttribute(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractSessionPersistent.removeAttribute(String)"})
  public void testRemoveAttribute() {
    // Arrange, Act and Assert
    assertNull(new LocalWorkspaceSession(mock(DBPWorkspace.class)).removeAttribute("Name"));
  }
}
