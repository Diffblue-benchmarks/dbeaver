package org.jkiss.dbeaver.registry;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.registry.DataSourceDescriptor.ContextInfo;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceDescriptorDiffblueTest {
  /**
   * Test ContextInfo getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ContextInfo#ContextInfo(DBCExecutionContext)}
   *   <li>{@link ContextInfo#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContextInfo.<init>(DBCExecutionContext)",
    "java.lang.String ContextInfo.toString()"
  })
  public void testContextInfoGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new ContextInfo(mock(DBCExecutionContext.class)).toString());
  }
}
