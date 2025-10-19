package org.jkiss.dbeaver.model.impl.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.imageio.metadata.IIOMetadataNode;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.w3c.dom.Document;

@RunWith(MockitoJUnitRunner.class)
public class DBDDocumentXMLDiffblueTest {
  @InjectMocks private DBDDocumentXML dBDDocumentXML;

  @Mock private Document document;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDDocumentXML#DBDDocumentXML(Document)}
   *   <li>{@link DBDDocumentXML#release()}
   *   <li>{@link DBDDocumentXML#getDocumentContentType()}
   *   <li>{@link DBDDocumentXML#getRawValue()}
   *   <li>{@link DBDDocumentXML#isModified()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDDocumentXML.<init>(Document)",
    "String DBDDocumentXML.getDocumentContentType()",
    "Object DBDDocumentXML.getRawValue()",
    "boolean DBDDocumentXML.isModified()",
    "void DBDDocumentXML.release()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBDDocumentXML actualDbdDocumentXML = new DBDDocumentXML(null);
    actualDbdDocumentXML.release();
    String actualDocumentContentType = actualDbdDocumentXML.getDocumentContentType();
    Object actualRawValue = actualDbdDocumentXML.getRawValue();

    // Assert
    assertEquals("text/xml", actualDocumentContentType);
    assertNull(actualRawValue);
    assertFalse(actualDbdDocumentXML.isModified());
  }

  /**
   * Test {@link DBDDocumentXML#getDocumentId()}.
   *
   * <p>Method under test: {@link DBDDocumentXML#getDocumentId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBDDocumentXML.getDocumentId()"})
  public void testGetDocumentId() {
    // Arrange
    when(document.getDocumentURI()).thenReturn("Document URI");

    // Act
    Object actualDocumentId = dBDDocumentXML.getDocumentId();

    // Assert
    verify(document).getDocumentURI();
    assertEquals("Document URI", actualDocumentId);
  }

  /**
   * Test {@link DBDDocumentXML#getDocumentProperty(String)}.
   *
   * <ul>
   *   <li>Given {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDocumentXML#getDocumentProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBDDocumentXML.getDocumentProperty(String)"})
  public void testGetDocumentProperty_givenDBDDocumentXMLWithDocumentIsNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DBDDocumentXML(null).getDocumentProperty("Name"));
  }

  /**
   * Test {@link DBDDocumentXML#getDocumentProperty(String)}.
   *
   * <ul>
   *   <li>Then return {@code Document URI}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDocumentXML#getDocumentProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBDDocumentXML.getDocumentProperty(String)"})
  public void testGetDocumentProperty_thenReturnDocumentUri() {
    // Arrange
    when(document.getDocumentURI()).thenReturn("Document URI");

    // Act
    Object actualDocumentProperty = dBDDocumentXML.getDocumentProperty("id");

    // Assert
    verify(document).getDocumentURI();
    assertEquals("Document URI", actualDocumentProperty);
  }

  /**
   * Test {@link DBDDocumentXML#getRootNode()}.
   *
   * <p>Method under test: {@link DBDDocumentXML#getRootNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DBDDocumentXML.getRootNode()"})
  public void testGetRootNode() {
    // Arrange
    IIOMetadataNode iioMetadataNode = new IIOMetadataNode();
    when(document.getDocumentElement()).thenReturn(iioMetadataNode);

    // Act
    Object actualRootNode = dBDDocumentXML.getRootNode();

    // Assert
    verify(document).getDocumentElement();
    assertSame(iioMetadataNode, actualRootNode);
  }

  /**
   * Test {@link DBDDocumentXML#serializeDocument(DBRProgressMonitor, Writer)}.
   *
   * <p>Method under test: {@link DBDDocumentXML#serializeDocument(DBRProgressMonitor, Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDocumentXML.serializeDocument(DBRProgressMonitor, Writer)"})
  public void testSerializeDocument() throws IOException, DBException {
    // Arrange
    DBDDocumentXML dbdDocumentXML = new DBDDocumentXML(null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    StringWriter writer = new StringWriter();

    // Act
    dbdDocumentXML.serializeDocument(monitor, writer);

    // Assert
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", writer.toString());
  }

  /**
   * Test {@link DBDDocumentXML#serializeDocument(DBRProgressMonitor, Writer)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDocumentXML#serializeDocument(DBRProgressMonitor, Writer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDocumentXML.serializeDocument(DBRProgressMonitor, Writer)"})
  public void testSerializeDocument_whenNull_thenThrowDBException()
      throws IOException, DBException {
    // Arrange
    DBDDocumentXML dbdDocumentXML = new DBDDocumentXML(null);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dbdDocumentXML.serializeDocument(new LoggingProgressMonitor(), null));
  }

  /**
   * Test {@link DBDDocumentXML#updateDocument(DBRProgressMonitor, Reader)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}
   *       DocumentId is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDocumentXML#updateDocument(DBRProgressMonitor, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDocumentXML.updateDocument(DBRProgressMonitor, Reader)"})
  public void testUpdateDocument_whenNull_thenDBDDocumentXMLWithDocumentIsNullDocumentIdIsNull()
      throws IOException, DBException {
    // Arrange
    DBDDocumentXML dbdDocumentXML = new DBDDocumentXML(null);

    // Act
    dbdDocumentXML.updateDocument(new LoggingProgressMonitor(), null);

    // Assert
    assertNull(dbdDocumentXML.getDocumentId());
    assertNull(dbdDocumentXML.getRootNode());
    assertFalse(dbdDocumentXML.isNull());
    assertTrue(dbdDocumentXML.isModified());
  }

  /**
   * Test {@link DBDDocumentXML#updateDocument(DBRProgressMonitor, Reader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDocumentXML#updateDocument(DBRProgressMonitor, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBDDocumentXML.updateDocument(DBRProgressMonitor, Reader)"})
  public void testUpdateDocument_whenStringReaderWithFoo_thenThrowDBException()
      throws IOException, DBException {
    // Arrange
    DBDDocumentXML dbdDocumentXML = new DBDDocumentXML(null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        DBException.class, () -> dbdDocumentXML.updateDocument(monitor, new StringReader("foo")));
  }

  /**
   * Test {@link DBDDocumentXML#isNull()}.
   *
   * <ul>
   *   <li>Given {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDocumentXML#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDocumentXML.isNull()"})
  public void testIsNull_givenDBDDocumentXMLWithDocumentIsNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new DBDDocumentXML(null).isNull());
  }

  /**
   * Test {@link DBDDocumentXML#isNull()}.
   *
   * <ul>
   *   <li>Given {@link Document}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDocumentXML#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDDocumentXML.isNull()"})
  public void testIsNull_givenDocument_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(dBDDocumentXML.isNull());
  }
}
