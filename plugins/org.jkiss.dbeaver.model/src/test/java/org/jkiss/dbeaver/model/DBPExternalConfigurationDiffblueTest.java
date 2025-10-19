package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.runtime.DBRInvoker;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPExternalConfigurationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPExternalConfiguration#DBPExternalConfiguration(String, DBRInvoker)}
   *   <li>{@link DBPExternalConfiguration#getId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPExternalConfiguration.<init>(String, DBRInvoker)",
    "String DBPExternalConfiguration.getId()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBPExternalConfiguration actualDbpExternalConfiguration =
        new DBPExternalConfiguration("42", mock(DBRInvoker.class));

    // Assert
    assertEquals("42", actualDbpExternalConfiguration.getId());
  }

  /**
   * Test {@link DBPExternalConfiguration#getProperties()}.
   *
   * <p>Method under test: {@link DBPExternalConfiguration#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBPExternalConfiguration.getProperties()"})
  public void testGetProperties() throws DBException {
    // Arrange
    DBRInvoker<Map<String, Object>> propertiesGetter = mock(DBRInvoker.class);
    when(propertiesGetter.invoke()).thenThrow(new DBException("An error occurred"));
    DBPExternalConfiguration dbpExternalConfiguration =
        new DBPExternalConfiguration("42", propertiesGetter);

    // Act
    Map<String, Object> actualProperties = dbpExternalConfiguration.getProperties();

    // Assert
    verify(propertiesGetter).invoke();
    assertTrue(actualProperties.isEmpty());
  }

  /**
   * Test {@link DBPExternalConfiguration#getProperties()}.
   *
   * <ul>
   *   <li>Given {@link DBRInvoker} {@link DBRInvoker#invoke()} return {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBPExternalConfiguration#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBPExternalConfiguration.getProperties()"})
  public void testGetProperties_givenDBRInvokerInvokeReturnHashMap() throws DBException {
    // Arrange
    DBRInvoker<Map<String, Object>> propertiesGetter = mock(DBRInvoker.class);
    when(propertiesGetter.invoke()).thenReturn(new HashMap<>());
    DBPExternalConfiguration dbpExternalConfiguration =
        new DBPExternalConfiguration("42", propertiesGetter);

    // Act
    Map<String, Object> actualProperties = dbpExternalConfiguration.getProperties();

    // Assert
    verify(propertiesGetter).invoke();
    assertTrue(actualProperties.isEmpty());
  }
}
