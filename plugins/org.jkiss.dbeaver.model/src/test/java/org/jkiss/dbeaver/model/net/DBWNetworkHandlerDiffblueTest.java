package org.jkiss.dbeaver.model.net;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.net.HTTPTunnelImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBWNetworkHandlerDiffblueTest {
  /**
   * Test {@link DBWNetworkHandler#getDependentDataSources()}.
   *
   * <p>Method under test: {@link DBWNetworkHandler#getDependentDataSources()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.DBPDataSourceContainer[] DBWNetworkHandler.getDependentDataSources()"
  })
  public void testGetDependentDataSources() {
    // Arrange, Act and Assert
    assertEquals(0, new HTTPTunnelImpl().getDependentDataSources().length);
  }
}
