package org.jkiss.dbeaver.model.impl.net;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.DBCInvalidatePhase;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SocksProxyImplDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SocksProxyImpl}
   *   <li>{@link SocksProxyImpl#invalidateHandler(DBRProgressMonitor, DBPDataSource,
   *       DBCInvalidatePhase)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SocksProxyImpl.<init>()",
    "void SocksProxyImpl.invalidateHandler(DBRProgressMonitor, DBPDataSource, DBCInvalidatePhase)"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange and Act
    SocksProxyImpl actualSocksProxyImpl = new SocksProxyImpl();
    actualSocksProxyImpl.invalidateHandler(
        new LoggingProgressMonitor(),
        mock(DBPDataSource.class),
        DBCInvalidatePhase.BEFORE_INVALIDATE);

    // Assert
    assertEquals(0, actualSocksProxyImpl.getDependentDataSources().length);
  }
}
