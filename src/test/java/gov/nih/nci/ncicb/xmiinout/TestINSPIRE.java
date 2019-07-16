package gov.nih.nci.ncicb.xmiinout;/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */


import gov.nih.nci.ncicb.xmiinout.domain.*;
import gov.nih.nci.ncicb.xmiinout.handler.HandlerEnum;
import gov.nih.nci.ncicb.xmiinout.handler.XmiException;
import gov.nih.nci.ncicb.xmiinout.handler.XmiHandlerFactory;
import gov.nih.nci.ncicb.xmiinout.handler.XmiInOutHandler;
import gov.nih.nci.ncicb.xmiinout.util.ModelUtil;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestINSPIRE {

  private static Logger logger = Logger.getLogger(TestINSPIRE.class.getName());
  private static String INSPIRE_CONSOLIDATED_UML_MODEL = "INSPIRE Consolidated UML Model ANNEX I II III complete r4618.xml";
  private XmiInOutHandler handler = null;
  private String filename;

  private void testGetModel() {
    UMLModel model = handler.getModel();
    System.out.println("Model: " + model.getName());
  }

  private UMLModel testGetModel(String modelName) {
    UMLModel model = handler.getModel(modelName);
    System.out.println("Model: " + model.getName());
    return model;
  }

  private void testLoadModel(String f) {
    try {
      handler.load("build/" + f);
    } catch (IOException | XmiException e) {
      e.printStackTrace();
    }
  }

  private void testSaveModel(String filename) {
    try {
      handler.save("build/" + filename + ".new.xmi");
    } catch (Exception e) {
      e.printStackTrace();
    } // end of try-catch
  }


  private void testSaveModel() {
    try {
      handler.save(filename + ".new.xmi");
    } catch (Exception e) {
      e.printStackTrace();
    } // end of try-catch
  }

  @Test
  public void testSuite() {
    init(INSPIRE_CONSOLIDATED_UML_MODEL);
    UMLModel model = testGetModel("EA Model");
    String packagePath = "INSPIRE Consolidated UML Model.Foundation Schemas.EarthResourceML.EarthResource.MineralOccurrence";
    testFindPackage(model, packagePath);
    testFindClass(model, packagePath + ".ExplorationActivity");
    testFindAttribute(model, packagePath + ".ExplorationActivity.activityDuration");
    testGetFullPackageName(model, packagePath + ".ExplorationActivity");
    testGetSuperclasses(model);
    addTaggedValueToAll(model);
    addDependency(model);
    testRemoveTaggedValue(model, packagePath + ".ExplorationActivity", "documentation");
    testRemoveTaggedValue(model, packagePath + ".ExplorationActivity.activityDuration", "ea_guid");
  }

  @Test
  public void testClassRemoveTaggedValue() {
    init(INSPIRE_CONSOLIDATED_UML_MODEL);
    UMLModel model = testGetModel("EA Model");
    String classPath = "INSPIRE Consolidated UML Model.Foundation Schemas.EarthResourceML.EarthResource.MineralOccurrence.ExplorationActivity";
    testTaggedValuePresent(model, classPath, "version", true);
    testRemoveTaggedValue(model, classPath, "version");
    testSaveModel(INSPIRE_CONSOLIDATED_UML_MODEL);
    testLoadModel(INSPIRE_CONSOLIDATED_UML_MODEL + ".new.xmi");
    model = testGetModel("EA Model");
    testTaggedValuePresent(model, classPath, "version", false);
  }

  @Test
  public void testPackageTaggedValue() {
    logger.info("Package Test Suite");
    init(INSPIRE_CONSOLIDATED_UML_MODEL);
    UMLModel model = testGetModel("EA Model");
    // testing Presence
    logger.info("Test version Presence");
    testTaggedValuePresent(model, "INSPIRE Consolidated UML Model", "usedtd", "FALSE");
    testTaggedValuePresent(model, "INSPIRE Consolidated UML Model.Foundation Schemas", "usedtd", "FALSE");
    testTaggedValuePresent(model, "INSPIRE Consolidated UML Model.Foundation Schemas.EarthResourceML", "usedtd", "FALSE");
    // testing Remove
    logger.info("Test TV Remove");
    testRemoveTaggedValue(model, "INSPIRE Consolidated UML Model", "usedtd");
    testRemoveTaggedValue(model, "INSPIRE Consolidated UML Model.Foundation Schemas", "usedtd");
    testRemoveTaggedValue(model, "INSPIRE Consolidated UML Model.Foundation Schemas.EarthResourceML", "usedtd");
    testSaveModel(INSPIRE_CONSOLIDATED_UML_MODEL);
    testLoadModel(INSPIRE_CONSOLIDATED_UML_MODEL + ".new.xmi");
    model = testGetModel("EA Model");
    testTaggedValuePresent(model, "INSPIRE Consolidated UML Model", "usedtd", false);
    testTaggedValuePresent(model, "INSPIRE Consolidated UML Model.Foundation Schemas", "usedtd", false);
    testTaggedValuePresent(model, "INSPIRE Consolidated UML Model.Foundation Schemas.EarthResourceML", "usedtd", false);
    // testing ADD
    logger.info("Test add element");
    init(INSPIRE_CONSOLIDATED_UML_MODEL);
    model = testGetModel("EA Model");
    testAddTaggedValue(model, "INSPIRE Consolidated UML Model", "new tag", "new value");
    testAddTaggedValue(model, "INSPIRE Consolidated UML Model.Foundation Schemas", "new tag", "new value");
    testAddTaggedValue(model, "INSPIRE Consolidated UML Model.Foundation Schemas.EarthResourceML", "new tag", "new value");
    testSaveModel(INSPIRE_CONSOLIDATED_UML_MODEL);
    testLoadModel(INSPIRE_CONSOLIDATED_UML_MODEL + ".new.xmi");
    model = testGetModel("EA Model");
    testTaggedValuePresent(model, "INSPIRE Consolidated UML Model", "new tag", "new value");
    testTaggedValuePresent(model, "INSPIRE Consolidated UML Model.Foundation Schemas", "new tag", "new value");
    testTaggedValuePresent(model, "INSPIRE Consolidated UML Model.Foundation Schemas.EarthResourceML", "new tag", "new value");
  }


  private void testGetSuperclasses(UMLModel model) {
    String packagePath = "INSPIRE Consolidated UML Model.Foundation Schemas.GeoSciML.GeoSciML-Core.GeologicStructure";
    UMLClass[] classes = testGetSuperclasses(model, packagePath + ".GeologicStructure");
    Assert.assertEquals(1, classes.length);
    Assert.assertEquals("GeologicFeature", classes[0].getName());
    System.out.println("Found superclass: " + classes[0].getName());
    classes = testGetSuperclasses(model, packagePath + ".ContactTypeTerm");
    Assert.assertEquals(0, classes.length);
    System.out.println("Correctly found no superclass for DomainObject");
  }

  private UMLClass[] testGetSuperclasses(UMLModel model, String className) {
    UMLClass clazz = ModelUtil.findClass(model, className);
    return ModelUtil.getSuperclasses(clazz);
  }


  private void testGetFullPackageName(UMLModel model, String className) {
    String pkgName = className.substring(0, className.lastIndexOf("."));
    UMLClass clazz = ModelUtil.findClass(model, className);
    String result = ModelUtil.getFullPackageName(clazz);
    Assert.assertEquals("Found Wrong Package Name: " + result + "   For Class : " + className, pkgName, result);
    System.out.println("Correct Package: " + result);
  }

  private void testFindPackage(UMLModel model, String fullName) {
    UMLPackage pkg = ModelUtil.findPackage(model, fullName);
    Assert.assertNotNull("Package not found -- " + fullName, pkg);
  }

  private void testFindClass(UMLModel model, String fullClassName) {
    UMLClass clazz = ModelUtil.findClass(model, fullClassName);
    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
    Assert.assertNotNull("Class not found -- " + fullClassName + " -- " + className, clazz);
    Assert.assertEquals("Class not found -- " + fullClassName + " -- " + className, clazz.getName(), className);
    System.out.println("Found Class: " + clazz.getName());
  }

  private void testFindAttribute(UMLModel model, String fullAttName) {
    UMLAttribute att = ModelUtil.findAttribute(model, fullAttName);
    String attName = fullAttName.substring(fullAttName.lastIndexOf(".") + 1);
    Assert.assertNotNull("Attrubute not found -- " + fullAttName + " -- " + attName, att);
    Assert.assertEquals("Attribute not found -- " + fullAttName + " -- " + attName, att.getName(), attName);
    System.out.println("Found Attribute: " + att.getName());
  }


  private void addDependency(UMLModel model) {
    UMLClass client,
            supplier;
    client = findClass(model, "Foliation");
    supplier = findClass(model, "Fracture");
    UMLDependency dep = model.createDependency(client, supplier, "relation");
    dep = model.addDependency(dep);
    dep.addTaggedValue("ea_type", "Dependency");
    dep.addTaggedValue("direction", "Source -&gt; Destination");
    dep.addTaggedValue("style", "3");
  }

  private UMLClass findClass(UMLModel model, String className) {
    for (UMLPackage pkg : model.getPackages()) {
      UMLClass c = findClass(pkg, className);
      if (c != null)
        return c;
    }
    return null;
  }

  private UMLClass findClass(UMLPackage pkg, String className) {
    for (UMLClass clazz : pkg.getClasses()) {
      if (clazz.getName().equals(className))
        return clazz;
    }
    for (UMLPackage _pkg : pkg.getPackages()) {
      UMLClass c = findClass(_pkg, className);
      if (c != null)
        return c;
    }
    return null;
  }


  private void testTaggedValuePresent(UMLModel model, String fullName, String tvName, String value) {
    UMLPackage pkg = ModelUtil.findPackage(model, fullName);
    if (pkg != null) {
      testTaggedValuePresent(pkg, tvName, value);
    } else {
      UMLClass clazz = ModelUtil.findClass(model, fullName);

      if (clazz != null) {
        testTaggedValuePresent(clazz, tvName, value);
      } else {
        UMLAttribute att = ModelUtil.findAttribute(model, fullName);
        if (att != null) {
          testTaggedValuePresent(att, tvName, value);
        } else {
          Assert.fail("testTaggedValuePresent element can't be found: " + fullName);
        }
      }
    }
  }

  private void testTaggedValuePresent(UMLModel model, String fullName, String tvName, boolean present) {
    UMLPackage pkg = ModelUtil.findPackage(model, fullName);
    if (pkg != null) {
      testTaggedValuePresent(pkg, tvName, present);
    } else {
      UMLClass clazz = ModelUtil.findClass(model, fullName);

      if (clazz != null) {
        testTaggedValuePresent(clazz, tvName, present);
      } else {
        UMLAttribute att = ModelUtil.findAttribute(model, fullName);
        if (att != null) {
          testTaggedValuePresent(att, tvName, present);
        } else {
          Assert.fail("testTaggedValuePresent element can't be found: " + fullName);
        }
      }
    }

  }

  private void testTaggedValuePresent(UMLTaggableElement elt, String tvName, String value) {
    UMLTaggedValue tv = elt.getTaggedValue(tvName);
    Assert.assertNotNull("testTaggedValuePresent Failed. " + elt + " -- " + tvName + " -- " + value, tv);
    Assert.assertEquals("testTaggedValuePresent Failed. " + elt + " -- " + tvName + " -- " + value, tv.getValue(), value);
  }

  private void testTaggedValuePresent(UMLTaggableElement elt, String tvName, boolean present) {
    UMLTaggedValue tv = elt.getTaggedValue(tvName);

    Assert.assertTrue("testTaggedValuePresent Failed. " + elt + " -- " + tvName + " -- " + present, (tv == null) != present);

  }


  private void testRemoveTaggedValue(UMLTaggableElement elt, String tvName) {
    elt.removeTaggedValue(tvName);
  }

  private void testRemoveTaggedValue(UMLModel model, String fullName, String tvName) {
    UMLPackage pkg = ModelUtil.findPackage(model, fullName);
    if (pkg != null) {
      testRemoveTaggedValue(pkg, tvName);
    } else {
      UMLClass clazz = ModelUtil.findClass(model, fullName);
      if (clazz != null) {
        testRemoveTaggedValue(clazz, tvName);
      } else {
        UMLAttribute att = ModelUtil.findAttribute(model, fullName);
        if (att != null) {
          testRemoveTaggedValue(att, tvName);
        } else {
          Assert.fail("testRemoveTaggedValue element can't be found: " + fullName);
        }
      }
    }
  }

  private void testAddTaggedValue(UMLTaggableElement elt, String tvName, String tvValue) {
    elt.addTaggedValue(tvName, tvValue);
  }

  private void testAddTaggedValue(UMLModel model, String fullName, String tvName, String tvValue) {
    UMLPackage pkg = ModelUtil.findPackage(model, fullName);
    if (pkg != null) {
      testAddTaggedValue(pkg, tvName, tvValue);
    } else {
      UMLClass clazz = ModelUtil.findClass(model, fullName);
      if (clazz != null) {
        testAddTaggedValue(clazz, tvName, tvValue);
      } else {
        UMLAttribute att = ModelUtil.findAttribute(model, fullName);
        if (att != null) {
          testAddTaggedValue(att, tvName, tvValue);
        } else {
          Assert.fail("testAddTaggedValue element can't be found: " + fullName);
        }
      }
    }
  }


  private void addTaggedValueToAll(UMLModel model) {
    for (UMLPackage pkg : model.getPackages()) {
      addTaggedValueToAll(pkg);
    }
    for (UMLClass clazz : model.getClasses()) {
      addTaggedValueToAll(clazz);
    }
  }

  private void addTaggedValueToAll(UMLPackage pkg) {
    pkg.addTaggedValue("myPackageTaggedValue", "test package tagged Value");

    for (UMLPackage _pkg : pkg.getPackages()) {
      addTaggedValueToAll(_pkg);
    }
    for (UMLClass clazz : pkg.getClasses()) {
      addTaggedValueToAll(clazz);
    }
  }

  private void addTaggedValueToAll(UMLClass clazz) {
    clazz.addTaggedValue("myClassTaggedValue", "test 123");

    for (UMLAttribute att : clazz.getAttributes()) {
      addTaggedValueToAll(att);
    }

    for (UMLAssociation assoc : clazz.getAssociations()) {
      addTaggedValueToAll(assoc);
    }
  }

  private void addTaggedValueToAll(UMLAttribute att) {
    att.addTaggedValue("myAttributeTaggedValue", "A boring, highly uninteresting value");
  }

  private void addTaggedValueToAll(UMLAssociation assoc) {
    assoc.addTaggedValue("myAssociationTaggedValue", "Fill in Assoc TV here.");
    for (UMLAssociationEnd end : assoc.getAssociationEnds()) {
      end.addTaggedValue("myAssociationEndTaggedValue", "Fill in AssocEND TV here.");
    }

  }

  private void init(String filename) {
    try {
      handler = XmiHandlerFactory.getXmiHandler(HandlerEnum.EADefault);
      handler.load("src/test/resources/testdata/" + filename);
    } catch (XmiException | IOException e) {
      e.printStackTrace();
    }
  }


  private void init() {
    try {
      handler = XmiHandlerFactory.getXmiHandler(HandlerEnum.EADefault);
      handler.load(filename);
    } catch (XmiException | IOException e) {
      e.printStackTrace();
    }
  }
}
