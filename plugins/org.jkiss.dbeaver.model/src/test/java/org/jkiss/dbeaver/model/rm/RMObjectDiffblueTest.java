package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RMObjectDiffblueTest {
  /**
   * Test {@link RMObject#getName()}.
   *
   * <p>Method under test: {@link RMObject#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMObject.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals("anonymous", RMUtils.createAnonymousProject().getName());
  }

  /**
   * Test {@link RMObject#setName(String)}.
   *
   * <p>Method under test: {@link RMObject#setName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMObject.setName(String)"})
  public void testSetName() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();

    // Act
    createAnonymousProjectResult.setName("Name");

    // Assert
    assertEquals("Name", createAnonymousProjectResult.getName());
  }

  /**
   * Test {@link RMObject#getChildren()}.
   *
   * <p>Method under test: {@link RMObject#getChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RMResource[] RMObject.getChildren()"})
  public void testGetChildren() {
    // Arrange, Act and Assert
    assertNull(RMUtils.createAnonymousProject().getChildren());
  }

  /**
   * Test {@link RMObject#setChildren(RMResource[])}.
   *
   * <p>Method under test: {@link RMObject#setChildren(RMResource[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMObject.setChildren(RMResource[])"})
  public void testSetChildren() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    RMResource[] resources = new RMResource[] {new RMResource("Name")};

    // Act
    createAnonymousProjectResult.setChildren(resources);

    // Assert
    assertSame(resources, createAnonymousProjectResult.getChildren());
  }

  /**
   * Test {@link RMObject#getChild(String)}.
   *
   * <ul>
   *   <li>Given createAnonymousProject addChild {@link RMResource#RMResource(String)} with name is
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link RMObject#getChild(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RMResource RMObject.getChild(String)"})
  public void testGetChild_givenCreateAnonymousProjectAddChildRMResourceWithNameIs42() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    createAnonymousProjectResult.addChild(new RMResource("42"));

    // Act and Assert
    assertNull(createAnonymousProjectResult.getChild("Name"));
  }

  /**
   * Test {@link RMObject#getChild(String)}.
   *
   * <ul>
   *   <li>Given createAnonymousProject.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RMObject#getChild(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RMResource RMObject.getChild(String)"})
  public void testGetChild_givenCreateAnonymousProject_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(RMUtils.createAnonymousProject().getChild("Name"));
  }

  /**
   * Test {@link RMObject#getChild(String)}.
   *
   * <ul>
   *   <li>Then return {@link RMResource#RMResource(String)} with {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link RMObject#getChild(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RMResource RMObject.getChild(String)"})
  public void testGetChild_thenReturnRMResourceWithName() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    RMResource rmResource = new RMResource("Name");
    rmProject.setChildren(new RMResource[] {rmResource});

    // Act and Assert
    assertSame(rmResource, rmProject.getChild("Name"));
  }

  /**
   * Test {@link RMObject#updateChild(String, RMResource)}.
   *
   * <p>Method under test: {@link RMObject#updateChild(String, RMResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMObject.updateChild(String, RMResource)"})
  public void testUpdateChild() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    rmProject.setChildren(new RMResource[] {new RMResource("Name")});
    RMResource resource = new RMResource("Name");

    // Act
    rmProject.updateChild("Name", resource);

    // Assert that nothing has changed
    RMResource[] children = rmProject.getChildren();
    assertEquals(1, children.length);
    assertSame(resource, children[0]);
  }

  /**
   * Test {@link RMObject#updateChild(String, RMResource)}.
   *
   * <ul>
   *   <li>Given {@link RMResource} {@link RMResource#getName()} return {@code Name}.
   *   <li>Then calls {@link RMResource#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link RMObject#updateChild(String, RMResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMObject.updateChild(String, RMResource)"})
  public void testUpdateChild_givenRMResourceGetNameReturnName_thenCallsGetName() {
    // Arrange
    RMResource child = mock(RMResource.class);
    when(child.getName()).thenReturn("Name");

    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    createAnonymousProjectResult.addChild(child);
    RMResource resource = new RMResource("Name");

    // Act
    createAnonymousProjectResult.updateChild("Name", resource);

    // Assert
    verify(child).getName();
    RMResource[] children = createAnonymousProjectResult.getChildren();
    assertEquals(1, children.length);
    assertSame(resource, children[0]);
  }

  /**
   * Test {@link RMObject#updateChild(String, RMResource)}.
   *
   * <ul>
   *   <li>Then first element is {@link RMResource#RMResource(String)} with name is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link RMObject#updateChild(String, RMResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMObject.updateChild(String, RMResource)"})
  public void testUpdateChild_thenFirstElementIsRMResourceWithNameIs42() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    RMResource child = new RMResource("42");
    createAnonymousProjectResult.addChild(child);

    // Act
    createAnonymousProjectResult.updateChild("Name", new RMResource("Name"));

    // Assert that nothing has changed
    RMResource[] children = createAnonymousProjectResult.getChildren();
    assertEquals(1, children.length);
    assertSame(child, children[0]);
  }

  /**
   * Test {@link RMObject#addChild(RMResource)}.
   *
   * <ul>
   *   <li>Given createAnonymousProject.
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link RMObject#addChild(RMResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMObject.addChild(RMResource)"})
  public void testAddChild_givenCreateAnonymousProject_thenArrayLengthIsOne() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    RMResource child = new RMResource("Name");

    // Act
    createAnonymousProjectResult.addChild(child);

    // Assert
    RMResource[] children = createAnonymousProjectResult.getChildren();
    assertEquals(1, children.length);
    assertSame(child, children[0]);
  }

  /**
   * Test {@link RMObject#addChild(RMResource)}.
   *
   * <ul>
   *   <li>Then array length is two.
   * </ul>
   *
   * <p>Method under test: {@link RMObject#addChild(RMResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RMObject.addChild(RMResource)"})
  public void testAddChild_thenArrayLengthIsTwo() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    RMResource rmResource = new RMResource("Name");
    rmProject.setChildren(new RMResource[] {rmResource});
    RMResource child = new RMResource("Name");

    // Act
    rmProject.addChild(child);

    // Assert
    RMResource[] children = rmProject.getChildren();
    assertEquals(2, children.length);
    assertSame(rmResource, children[0]);
    assertSame(child, children[1]);
  }

  /**
   * Test {@link RMObject#toString()}.
   *
   * <p>Method under test: {@link RMObject#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMObject.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("Name", new RMResource("Name").toString());
  }
}
