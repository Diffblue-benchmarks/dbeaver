package org.jkiss.dbeaver.model.impl.struct;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.navigator.DBNDatabaseNode;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ContextDefaultObjectsReaderDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ContextDefaultObjectsReader#ContextDefaultObjectsReader(DBPDataSource,
   *       DBCExecutionContext)}
   *   <li>{@link ContextDefaultObjectsReader#setReadNodes(boolean)}
   *   <li>{@link ContextDefaultObjectsReader#getDataSource()}
   *   <li>{@link ContextDefaultObjectsReader#getDefaultCatalogName()}
   *   <li>{@link ContextDefaultObjectsReader#getDefaultObject()}
   *   <li>{@link ContextDefaultObjectsReader#getExecutionContext()}
   *   <li>{@link ContextDefaultObjectsReader#getNodeList()}
   *   <li>{@link ContextDefaultObjectsReader#getObjectList()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContextDefaultObjectsReader.<init>(DBPDataSource, DBCExecutionContext)",
    "DBPDataSource ContextDefaultObjectsReader.getDataSource()",
    "String ContextDefaultObjectsReader.getDefaultCatalogName()",
    "DBSObject ContextDefaultObjectsReader.getDefaultObject()",
    "DBCExecutionContext ContextDefaultObjectsReader.getExecutionContext()",
    "List ContextDefaultObjectsReader.getNodeList()",
    "java.util.Collection ContextDefaultObjectsReader.getObjectList()",
    "void ContextDefaultObjectsReader.setReadNodes(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act
    ContextDefaultObjectsReader actualContextDefaultObjectsReader =
        new ContextDefaultObjectsReader(dataSource, executionContext);
    actualContextDefaultObjectsReader.setReadNodes(true);
    DBPDataSource actualDataSource = actualContextDefaultObjectsReader.getDataSource();
    String actualDefaultCatalogName = actualContextDefaultObjectsReader.getDefaultCatalogName();
    DBSObject actualDefaultObject = actualContextDefaultObjectsReader.getDefaultObject();
    DBCExecutionContext actualExecutionContext =
        actualContextDefaultObjectsReader.getExecutionContext();
    List<DBNDatabaseNode> actualNodeList = actualContextDefaultObjectsReader.getNodeList();

    // Assert
    assertNull(actualDefaultCatalogName);
    assertNull(actualContextDefaultObjectsReader.getObjectList());
    assertNull(actualDefaultObject);
    assertTrue(actualNodeList.isEmpty());
    assertSame(dataSource, actualDataSource);
    assertSame(executionContext, actualExecutionContext);
  }
}
