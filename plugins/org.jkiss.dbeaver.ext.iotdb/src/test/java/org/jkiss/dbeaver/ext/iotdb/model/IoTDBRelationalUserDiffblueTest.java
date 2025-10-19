package org.jkiss.dbeaver.ext.iotdb.model;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.ext.iotdb.model.IoTDBRelationalUser.IoTDBDatabase;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IoTDBRelationalUserDiffblueTest {
  /**
   * Test IoTDBDatabase {@link IoTDBDatabase#IoTDBDatabase(String, List)}.
   *
   * <p>Method under test: {@link IoTDBDatabase#IoTDBDatabase(String, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void IoTDBDatabase.<init>(String, List)"})
  public void testIoTDBDatabaseNewIoTDBDatabase() {
    // Arrange and Act
    IoTDBDatabase actualIoTDBDatabase = new IoTDBDatabase("Name", new ArrayList<>());

    // Assert
    assertTrue(actualIoTDBDatabase.tables.isEmpty());
  }
}
