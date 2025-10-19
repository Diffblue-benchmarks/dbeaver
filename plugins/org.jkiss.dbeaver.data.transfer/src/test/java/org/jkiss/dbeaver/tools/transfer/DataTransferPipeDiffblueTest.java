package org.jkiss.dbeaver.tools.transfer;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferConsumer;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferProducer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataTransferPipeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataTransferPipe#DataTransferPipe(IDataTransferProducer, IDataTransferConsumer)}
   *   <li>{@link DataTransferPipe#setConsumer(IDataTransferConsumer)}
   *   <li>{@link DataTransferPipe#setProducer(IDataTransferProducer)}
   *   <li>{@link DataTransferPipe#getConsumer()}
   *   <li>{@link DataTransferPipe#getProducer()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferPipe.<init>(IDataTransferProducer, IDataTransferConsumer)",
    "IDataTransferConsumer DataTransferPipe.getConsumer()",
    "IDataTransferProducer DataTransferPipe.getProducer()",
    "void DataTransferPipe.setConsumer(IDataTransferConsumer)",
    "void DataTransferPipe.setProducer(IDataTransferProducer)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DatabaseTransferProducer producer = new DatabaseTransferProducer();

    // Act
    DataTransferPipe actualDataTransferPipe =
        new DataTransferPipe(producer, new DatabaseTransferConsumer());
    DatabaseTransferConsumer consumer = new DatabaseTransferConsumer();
    actualDataTransferPipe.setConsumer(consumer);
    DatabaseTransferProducer producer2 = new DatabaseTransferProducer();
    actualDataTransferPipe.setProducer(producer2);
    IDataTransferConsumer<?, ?> actualConsumer = actualDataTransferPipe.getConsumer();

    // Assert
    assertSame(consumer, actualConsumer);
    assertSame(producer2, actualDataTransferPipe.getProducer());
  }

  /**
   * Test {@link DataTransferPipe#initPipe(DataTransferSettings, int, int)}.
   *
   * <p>Method under test: {@link DataTransferPipe#initPipe(DataTransferSettings, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferPipe.initPipe(DataTransferSettings, int, int)"})
  public void testInitPipe() throws DBException {
    // Arrange
    DataTransferPipe dataTransferPipe = new DataTransferPipe(null, new DatabaseTransferConsumer());

    // Act and Assert
    assertThrows(DBException.class, () -> dataTransferPipe.initPipe(null, 1, 1));
  }

  /**
   * Test {@link DataTransferPipe#initPipe(DataTransferSettings, int, int)}.
   *
   * <p>Method under test: {@link DataTransferPipe#initPipe(DataTransferSettings, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferPipe.initPipe(DataTransferSettings, int, int)"})
  public void testInitPipe2() throws DBException {
    // Arrange
    DataTransferPipe dataTransferPipe = new DataTransferPipe(new DatabaseTransferProducer(), null);

    // Act and Assert
    assertThrows(DBException.class, () -> dataTransferPipe.initPipe(null, 1, 1));
  }
}
