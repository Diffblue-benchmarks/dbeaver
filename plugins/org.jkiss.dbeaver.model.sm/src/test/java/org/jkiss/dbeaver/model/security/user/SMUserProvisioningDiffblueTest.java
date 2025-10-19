package org.jkiss.dbeaver.model.security.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.security.user.SMUserProvisioning.SMUserProvisioningBuilder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMUserProvisioningDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMUserProvisioning#SMUserProvisioning(String, Map, String)}
   *   <li>{@link SMUserProvisioning#getAuthRole()}
   *   <li>{@link SMUserProvisioning#getMetaParameters()}
   *   <li>{@link SMUserProvisioning#getUserId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMUserProvisioning.<init>(String, Map, String)",
    "String SMUserProvisioning.getAuthRole()",
    "Map SMUserProvisioning.getMetaParameters()",
    "String SMUserProvisioning.getUserId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HashMap<String, String> metaParameters = new HashMap<>();

    // Act
    SMUserProvisioning actualSmUserProvisioning =
        new SMUserProvisioning("42", metaParameters, "Auth Role");
    String actualAuthRole = actualSmUserProvisioning.getAuthRole();
    Map<String, String> actualMetaParameters = actualSmUserProvisioning.getMetaParameters();

    // Assert
    assertEquals("42", actualSmUserProvisioning.getUserId());
    assertEquals("Auth Role", actualAuthRole);
    assertTrue(actualMetaParameters.isEmpty());
    assertSame(metaParameters, actualMetaParameters);
  }

  /**
   * Test SMUserProvisioningBuilder {@link SMUserProvisioningBuilder#aSMUserProvisioning()}.
   *
   * <p>Method under test: {@link SMUserProvisioningBuilder#aSMUserProvisioning()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMUserProvisioningBuilder SMUserProvisioningBuilder.aSMUserProvisioning()"})
  public void testSMUserProvisioningBuilderASMUserProvisioning() {
    // Arrange, Act and Assert
    SMUserProvisioning smUserProvisioning = SMUserProvisioningBuilder.aSMUserProvisioning().build();
    assertNull(smUserProvisioning.getAuthRole());
    assertNull(smUserProvisioning.getUserId());
    assertNull(smUserProvisioning.getMetaParameters());
  }

  /**
   * Test SMUserProvisioningBuilder {@link SMUserProvisioningBuilder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMUserProvisioningBuilder#build()}
   *   <li>{@link SMUserProvisioningBuilder#authRole(String)}
   *   <li>{@link SMUserProvisioningBuilder#metaParameters(Map)}
   *   <li>{@link SMUserProvisioningBuilder#userId(String)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SMUserProvisioningBuilder SMUserProvisioningBuilder.authRole(String)",
    "SMUserProvisioning SMUserProvisioningBuilder.build()",
    "SMUserProvisioningBuilder SMUserProvisioningBuilder.metaParameters(Map)",
    "SMUserProvisioningBuilder SMUserProvisioningBuilder.userId(String)"
  })
  public void testSMUserProvisioningBuilderBuild() {
    // Arrange and Act
    SMUserProvisioningBuilder actualAuthRoleResult =
        SMUserProvisioning.builder().authRole("Auth Role");
    HashMap<String, String> metaParameters = new HashMap<>();
    SMUserProvisioning actualSmUserProvisioning =
        actualAuthRoleResult.metaParameters(metaParameters).userId("42").build();

    // Assert
    assertEquals("42", actualSmUserProvisioning.getUserId());
    assertEquals("Auth Role", actualSmUserProvisioning.getAuthRole());
    Map<String, String> metaParameters2 = actualSmUserProvisioning.getMetaParameters();
    assertTrue(metaParameters2.isEmpty());
    assertSame(metaParameters, metaParameters2);
  }
}
