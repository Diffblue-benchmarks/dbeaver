package org.jkiss.dbeaver.model.access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBAAuthParametersDiffblueTest {
  /**
   * Test {@link DBAAuthParameters#getProperty(String)}.
   *
   * <p>Method under test: {@link DBAAuthParameters#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBAAuthParameters.getProperty(String)"})
  public void testGetProperty() {
    // Arrange, Act and Assert
    assertNull(new DBAAuthParameters().getProperty("Name"));
  }

  /**
   * Test {@link DBAAuthParameters#setProperty(String, Object)}.
   *
   * <p>Method under test: {@link DBAAuthParameters#setProperty(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBAAuthParameters.setProperty(String, Object)"})
  public void testSetProperty() {
    // Arrange
    DBAAuthParameters dbaAuthParameters = new DBAAuthParameters();
    Object object = DBPEvent.RENAME;

    // Act
    dbaAuthParameters.setProperty("Name", object);

    // Assert
    Map<String, Object> properties = dbaAuthParameters.getProperties();
    assertEquals(1, properties.size());
    assertSame(object, properties.get("Name"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBAAuthParameters}
   *   <li>{@link DBAAuthParameters#setCallbackHandler(CallbackHandler)}
   *   <li>{@link DBAAuthParameters#getCallbackHandler()}
   *   <li>{@link DBAAuthParameters#getProperties()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBAAuthParameters.<init>()",
    "CallbackHandler DBAAuthParameters.getCallbackHandler()",
    "Map DBAAuthParameters.getProperties()",
    "void DBAAuthParameters.setCallbackHandler(CallbackHandler)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBAAuthParameters actualDbaAuthParameters = new DBAAuthParameters();
    CallbackHandler callbackHandler = mock(CallbackHandler.class);
    actualDbaAuthParameters.setCallbackHandler(callbackHandler);
    CallbackHandler actualCallbackHandler = actualDbaAuthParameters.getCallbackHandler();

    // Assert
    assertTrue(actualDbaAuthParameters.getProperties().isEmpty());
    assertSame(callbackHandler, actualCallbackHandler);
  }
}
