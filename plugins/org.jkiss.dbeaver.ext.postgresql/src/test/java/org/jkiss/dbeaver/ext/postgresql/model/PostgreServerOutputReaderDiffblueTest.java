package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.output.DBCOutputSeverity;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreServerOutputReaderDiffblueTest {
  /**
   * Test {@link PostgreServerOutputReader#getSupportedSeverities(DBCExecutionContext)}.
   *
   * <p>Method under test: {@link
   * PostgreServerOutputReader#getSupportedSeverities(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBCOutputSeverity[] PostgreServerOutputReader.getSupportedSeverities(DBCExecutionContext)"
  })
  public void testGetSupportedSeverities() {
    // Arrange and Act
    DBCOutputSeverity[] actualSupportedSeverities =
        new PostgreServerOutputReader().getSupportedSeverities(mock(DBCExecutionContext.class));

    // Assert
    assertEquals(6, actualSupportedSeverities.length);
    assertFalse(actualSupportedSeverities[0].isForced());
    assertFalse(actualSupportedSeverities[1].isForced());
    assertFalse(actualSupportedSeverities[2].isForced());
    assertFalse(actualSupportedSeverities[3].isForced());
    assertFalse(actualSupportedSeverities[4].isForced());
    assertFalse(actualSupportedSeverities[5].isForced());
  }

  /**
   * Test new {@link PostgreServerOutputReader} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link PostgreServerOutputReader}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PostgreServerOutputReader.<init>()"})
  public void testNewPostgreServerOutputReader() {
    // Arrange and Act
    PostgreServerOutputReader actualPostgreServerOutputReader = new PostgreServerOutputReader();

    // Assert
    assertTrue(actualPostgreServerOutputReader.isAsyncOutputReadSupported());
    assertTrue(actualPostgreServerOutputReader.isServerOutputEnabled());
  }
}
