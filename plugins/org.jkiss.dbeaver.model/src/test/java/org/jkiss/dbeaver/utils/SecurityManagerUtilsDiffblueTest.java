package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.AllPermission;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.osgi.framework.AdminPermission;

public class SecurityManagerUtilsDiffblueTest {
  /**
   * Test {@link SecurityManagerUtils#getDefaultPermissions()}.
   *
   * <p>Method under test: {@link SecurityManagerUtils#getDefaultPermissions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SecurityManagerUtils.getDefaultPermissions()"})
  public void testGetDefaultPermissions() {
    // Arrange and Act
    List<Permission> actualDefaultPermissions = SecurityManagerUtils.getDefaultPermissions();

    // Assert
    assertEquals(9, actualDefaultPermissions.size());
    Permission getResult = actualDefaultPermissions.get(3);
    assertTrue(getResult instanceof AdminPermission);
    assertEquals("*", actualDefaultPermissions.get(0).getName());
    assertEquals("*", actualDefaultPermissions.get(1).getName());
    assertEquals("*", actualDefaultPermissions.get(2).getName());
    assertEquals("*", getResult.getName());
    assertEquals("*", actualDefaultPermissions.get(5).getName());
    assertEquals("accessDeclaredMembers", actualDefaultPermissions.get(4).getName());
    assertEquals(
        "class,execute,extensionLifecycle,lifecycle,listener,metadata,resolve,resource,startlevel,context"
            + ",weave",
        getResult.getActions());
    assertEquals("createClassLoader", actualDefaultPermissions.get(7).getName());
    assertEquals("getClassLoader", actualDefaultPermissions.get(6).getName());
    assertEquals("getenv.*", actualDefaultPermissions.get(8).getName());
  }

  /**
   * Test {@link SecurityManagerUtils#executeWithAccessControlContext(AccessControlContext,
   * Callable)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>When {@link Callable} {@link Callable#call()} return {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SecurityManagerUtils#executeWithAccessControlContext(AccessControlContext, Callable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SecurityManagerUtils.executeWithAccessControlContext(AccessControlContext, Callable)"
  })
  public void testExecuteWithAccessControlContext_givenRename_whenCallableCallReturnRename()
      throws Throwable {
    // Arrange
    AccessControlContext controlContext = AccessController.getContext();

    Callable<Object> callable = mock(Callable.class);
    when(callable.call()).thenReturn(DBPEvent.RENAME);

    // Act
    SecurityManagerUtils.executeWithAccessControlContext(controlContext, callable);

    // Assert
    verify(callable).call();
  }

  /**
   * Test {@link SecurityManagerUtils#executeWithAccessControlContext(AccessControlContext,
   * Callable)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SecurityManagerUtils#executeWithAccessControlContext(AccessControlContext, Callable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SecurityManagerUtils.executeWithAccessControlContext(AccessControlContext, Callable)"
  })
  public void testExecuteWithAccessControlContext_thenThrowRuntimeException() throws Throwable {
    // Arrange
    AccessControlContext controlContext = AccessController.getContext();

    Callable<Object> callable = mock(Callable.class);
    when(callable.call()).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> SecurityManagerUtils.executeWithAccessControlContext(controlContext, callable));
    verify(callable).call();
  }

  /**
   * Test {@link SecurityManagerUtils#controlContextOf(List)}.
   *
   * <ul>
   *   <li>Given {@link AdminPermission#AdminPermission()}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link AdminPermission#AdminPermission()}.
   * </ul>
   *
   * <p>Method under test: {@link SecurityManagerUtils#controlContextOf(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AccessControlContext SecurityManagerUtils.controlContextOf(List)"})
  public void testControlContextOf_givenAdminPermission_whenArrayListAddAdminPermission() {
    // Arrange
    ArrayList<Permission> permissions = new ArrayList<>();
    permissions.add(new AdminPermission());

    // Act
    AccessControlContext actualControlContextOfResult =
        SecurityManagerUtils.controlContextOf(permissions);

    // Assert
    assertNull(actualControlContextOfResult.getDomainCombiner());
  }

  /**
   * Test {@link SecurityManagerUtils#controlContextOf(List)}.
   *
   * <ul>
   *   <li>Given {@link AllPermission#AllPermission()}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link AllPermission#AllPermission()}.
   * </ul>
   *
   * <p>Method under test: {@link SecurityManagerUtils#controlContextOf(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AccessControlContext SecurityManagerUtils.controlContextOf(List)"})
  public void testControlContextOf_givenAllPermission_whenArrayListAddAllPermission() {
    // Arrange
    ArrayList<Permission> permissions = new ArrayList<>();
    permissions.add(new AllPermission());

    // Act
    AccessControlContext actualControlContextOfResult =
        SecurityManagerUtils.controlContextOf(permissions);

    // Assert
    assertNull(actualControlContextOfResult.getDomainCombiner());
  }

  /**
   * Test {@link SecurityManagerUtils#controlContextOf(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return DomainCombiner is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SecurityManagerUtils#controlContextOf(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AccessControlContext SecurityManagerUtils.controlContextOf(List)"})
  public void testControlContextOf_whenArrayList_thenReturnDomainCombinerIsNull() {
    // Arrange and Act
    AccessControlContext actualControlContextOfResult =
        SecurityManagerUtils.controlContextOf(new ArrayList<>());

    // Assert
    assertNull(actualControlContextOfResult.getDomainCombiner());
  }

  /**
   * Test {@link SecurityManagerUtils#wrapDriverActions(DBPDataSourceContainer, Callable)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link SecurityManagerUtils#wrapDriverActions(DBPDataSourceContainer,
   * Callable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SecurityManagerUtils.wrapDriverActions(DBPDataSourceContainer, Callable)"
  })
  public void testWrapDriverActions_givenRuntimeException_thenThrowRuntimeException()
      throws Throwable {
    // Arrange
    DBPDataSourceContainer container = mock(DBPDataSourceContainer.class);
    when(container.getDriver()).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> SecurityManagerUtils.wrapDriverActions(container, mock(Callable.class)));
    verify(container).getDriver();
  }
}
