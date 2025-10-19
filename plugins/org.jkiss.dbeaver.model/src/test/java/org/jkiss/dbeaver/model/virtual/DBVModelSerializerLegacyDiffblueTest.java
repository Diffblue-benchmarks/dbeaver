package org.jkiss.dbeaver.model.virtual;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.virtual.DBVModelSerializerLegacy.ModelParser;
import org.jkiss.utils.xml.SAXReader;
import org.jkiss.utils.xml.XMLBuilder;
import org.jkiss.utils.xml.XMLException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.xml.sax.Attributes;

public class DBVModelSerializerLegacyDiffblueTest {
  /**
   * Test ModelParser {@link ModelParser#saxStartElement(SAXReader, String, String, Attributes)}.
   *
   * <ul>
   *   <li>When {@code exclude}.
   *   <li>Then calls {@link Attributes#getValue(String)}.
   * </ul>
   *
   * <p>Method under test: {@link ModelParser#saxStartElement(SAXReader, String, String,
   * Attributes)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ModelParser.saxStartElement(SAXReader, String, String, Attributes)"})
  public void testModelParserSaxStartElement_whenExclude_thenCallsGetValue()
      throws UnsupportedEncodingException, XMLException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    ModelParser modelParser = new ModelParser(new DBVContainer(parent2, "Name"));
    SAXReader reader = new SAXReader(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    Attributes attributes = mock(Attributes.class);
    when(attributes.getValue(Mockito.<String>any())).thenReturn("");

    // Act
    modelParser.saxStartElement(reader, "Namespace URI", "exclude", attributes);

    // Assert
    verify(attributes).getValue("id");
  }

  /**
   * Test ModelParser {@link ModelParser#saxStartElement(SAXReader, String, String, Attributes)}.
   *
   * <ul>
   *   <li>When {@code include}.
   *   <li>Then calls {@link Attributes#getValue(String)}.
   * </ul>
   *
   * <p>Method under test: {@link ModelParser#saxStartElement(SAXReader, String, String,
   * Attributes)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ModelParser.saxStartElement(SAXReader, String, String, Attributes)"})
  public void testModelParserSaxStartElement_whenInclude_thenCallsGetValue()
      throws UnsupportedEncodingException, XMLException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    ModelParser modelParser = new ModelParser(new DBVContainer(parent2, "Name"));
    SAXReader reader = new SAXReader(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    Attributes attributes = mock(Attributes.class);
    when(attributes.getValue(Mockito.<String>any())).thenReturn("");

    // Act
    modelParser.saxStartElement(reader, "Namespace URI", "include", attributes);

    // Assert
    verify(attributes).getValue("id");
  }

  /**
   * Test ModelParser {@link ModelParser#saxStartElement(SAXReader, String, String, Attributes)}.
   *
   * <ul>
   *   <li>When {@code transform}.
   *   <li>Then calls {@link Attributes#getValue(String)}.
   * </ul>
   *
   * <p>Method under test: {@link ModelParser#saxStartElement(SAXReader, String, String,
   * Attributes)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ModelParser.saxStartElement(SAXReader, String, String, Attributes)"})
  public void testModelParserSaxStartElement_whenTransform_thenCallsGetValue()
      throws UnsupportedEncodingException, XMLException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    ModelParser modelParser = new ModelParser(new DBVContainer(parent2, "Name"));
    SAXReader reader = new SAXReader(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    Attributes attributes = mock(Attributes.class);
    when(attributes.getValue(Mockito.<String>any())).thenReturn("");

    // Act
    modelParser.saxStartElement(reader, "Namespace URI", "transform", attributes);

    // Assert
    verify(attributes).getValue("custom");
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer2() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", new HashMap<>());
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer3() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer4() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer5() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity.addForeignKey(new DBVEntityForeignKey(entity2));
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dbpDataSource, atLeast(1)).getContainer();
    verify(dbpDataSourceContainer, atLeast(1)).fireEvent(Mockito.<DBPEvent>any());
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer6() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    entity.addColorOverride(color);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer7() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("Name");

    DBVEntity entity3 = new DBVEntity(container, "Name", "");
    entity3.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity3);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer8() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.setUseAllColumns(true);
    constraint.addAttribute("Name");

    DBVEntity entity3 = new DBVEntity(container, "Name", "");
    entity3.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity3);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer9() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");
    container.addEntity(entity);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent5 = mock(DBVContainer.class);
    when(parent5.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent6 = new DBVContainer(parent5, "Name", new HashMap<>());

    DBVEntity entity2 = new DBVEntity(new DBVContainer(parent6, "Name"), "Name", "");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntity entity4 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity4, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity3, copy);
    entity2.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity2);
    object.addContainer(container);

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent5).getDataSource();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer10() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    entity.setProperty("Name", DBPEvent.RENAME);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(mock(DBVContainer.class), "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel source = new DBVModel(dataSourceContainer2);

    DBVModel object = new DBVModel(dataSourceContainer, source);
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <ul>
   *   <li>Given {@link DBVModelSerializer#ATTR_BACKGROUND}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer_givenAttr_background() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");
    xml.addText(DBVModelSerializer.ATTR_BACKGROUND);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel object = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder, DBVContainer)}.
   *
   * <ul>
   *   <li>Given {@code Element Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVModelSerializerLegacy#serializeContainer(XMLBuilder,
   * DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVModelSerializerLegacy.serializeContainer(XMLBuilder, DBVContainer)"})
  public void testSerializeContainer_givenElementName() throws IOException {
    // Arrange
    XMLBuilder xml = new XMLBuilder(new ByteArrayOutputStream(), "UTF-8");
    xml.addElement("Element Name", "42");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel object = new DBVModel(dataSourceContainer);
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    object.addEntity(entity);
    DBVContainer parent3 = mock(DBVContainer.class);
    DBVContainer parent4 = new DBVContainer(parent3, "Name", new HashMap<>());
    object.addContainer(new DBVContainer(parent4, "Name"));

    // Act
    DBVModelSerializerLegacy.serializeContainer(xml, object);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }
}
