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

public class TestEA2 {

  private static Logger logger = Logger.getLogger(TestEA2.class.getName());
  private XmiInOutHandler handler = null;
  private String filename;
  private boolean noColor = true;

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
    init("test-with-dependency.xmi");

    UMLModel model = testGetModel("EA Model");

    PrintModel.printModel(model, true);

    testFindPackage(model, "Logical View.Logical Model.com.ludet.hr.domain");

    testFindClass(model, "Logical View.Logical Model.com.ludet.hr.domain.Employee");

    testFindAttribute(model, "Logical View.Logical Model.com.ludet.hr.domain.Employee.firstName");

    testGetFullPackageName(model, "Logical View.Logical Model.com.ludet.hr.domain.Employee");

    testGetSuperclasses(model);

    addTaggedValueToAll(model);

    addDependency(model);

    testRemoveTaggedValue(model, "Logical View.Logical Model.com.ludet.hr.common.DomainObject", "HUMAN_REVIEWED");
    testRemoveTaggedValue(model, "Logical View.Logical Model.com.ludet.hr.common.DomainObject", "ea_ntype");

    testRemoveTaggedValue(model, "Logical View.Logical Model.com.ludet.hr.domain.Employee.firstName", "HUMAN_REVIEWED");

    PrintModel.printModel(model, true);
  }

  @Test
  public void testClassRemoveTaggedValue() {
    init("XMI_Handler_TEST.xmi");

    UMLModel model = testGetModel("EA Model");

    testTaggedValuePresent(model, "Domain Model.hr.domain.Company", "CADSR_Description", true);

    testRemoveTaggedValue(model, "Domain Model.hr.domain.Company", "CADSR_Description");

    testSaveModel("XMI_Handler_TEST.xmi");

    testLoadModel("XMI_Handler_TEST.xmi.new.xmi");

    model = testGetModel("EA Model");

    testTaggedValuePresent(model, "Domain Model.hr.domain.Company", "CADSR_Description", false);

  }

  @Test
  public void testPackageTaggedValue() {
    logger.info("Package Test Suite");

    init("XMI_Handler_TEST.xmi");

    UMLModel model = testGetModel("EA Model");

    // testing Presence
    logger.info("Test TV Presence");
    testTaggedValuePresent(model, "Domain Model.hr", "TEST_PACKAGE_TV", "test hr");
    testTaggedValuePresent(model, "Domain Model.hr.domain", "TEST_PACKAGE_TV", "test domain");
    testTaggedValuePresent(model, "Domain Model.hr.domain.companies", "TEST_PACKAGE_TV", "test companies");
    testTaggedValuePresent(model, "Domain Model.hr.common", "TEST_PACKAGE_TV", "test common");
    testTaggedValuePresent(model, "Domain Model", "TEST_PACKAGE_TV", "test Domain Model");


    // testing Remove
    logger.info("Test TV Remove");
    testRemoveTaggedValue(model, "Domain Model.hr", "TEST_PACKAGE_TV");
    testRemoveTaggedValue(model, "Domain Model.hr.domain.companies", "TEST_PACKAGE_TV");
    testRemoveTaggedValue(model, "Domain Model.hr.domain", "TEST_PACKAGE_TV");
    testRemoveTaggedValue(model, "Domain Model.hr.common", "TEST_PACKAGE_TV");
    testRemoveTaggedValue(model, "Domain Model", "TEST_PACKAGE_TV");

    testSaveModel("XMI_Handler_TEST.xmi");

    testLoadModel("XMI_Handler_TEST.xmi.new.xmi");
    model = testGetModel("EA Model");

    testTaggedValuePresent(model, "Domain Model", "TEST_PACKAGE_TV", false);
    testTaggedValuePresent(model, "Domain Model.hr", "TEST_PACKAGE_TV", false);
    testTaggedValuePresent(model, "Domain Model.hr.domain", "TEST_PACKAGE_TV", false);
    testTaggedValuePresent(model, "Domain Model.hr.domain.companies", "TEST_PACKAGE_TV", false);
    testTaggedValuePresent(model, "Domain Model.hr.common", "TEST_PACKAGE_TV", false);


    // testing ADD
    logger.info("Test TV Add");
    init("XMI_Handler_TEST.xmi");
    model = testGetModel("EA Model");

    testAddTaggedValue(model, "Domain Model", "NEW_PACKAGE_TV", "new domain model");
    testAddTaggedValue(model, "Domain Model.hr", "NEW_PACKAGE_TV", "new hr");
    testAddTaggedValue(model, "Domain Model.hr.domain", "NEW_PACKAGE_TV", "new domain");
    testAddTaggedValue(model, "Domain Model.hr.domain.companies", "NEW_PACKAGE_TV", "new companies");
    testAddTaggedValue(model, "Domain Model.hr.common", "NEW_PACKAGE_TV", "new common");

    testSaveModel("XMI_Handler_TEST.xmi");

    testLoadModel("XMI_Handler_TEST.xmi.new.xmi");
    model = testGetModel("EA Model");

    testTaggedValuePresent(model, "Domain Model", "NEW_PACKAGE_TV", "new domain model");
    testTaggedValuePresent(model, "Domain Model.hr", "NEW_PACKAGE_TV", "new hr");
    testTaggedValuePresent(model, "Domain Model.hr.domain", "NEW_PACKAGE_TV", "new domain");
    testTaggedValuePresent(model, "Domain Model.hr.domain.companies", "NEW_PACKAGE_TV", "new companies");
    testTaggedValuePresent(model, "Domain Model.hr.common", "NEW_PACKAGE_TV", "new common");

  }


  private void testGetSuperclasses(UMLModel model) {
    UMLClass[] classes = testGetSuperclasses(model, "Logical View.Logical Model.com.ludet.hr.domain.Manager");
    Assert.assertEquals(1, classes.length);
    Assert.assertEquals("Employee", classes[0].getName());
    System.out.println("Found superclass: " + classes[0].getName());

    classes = testGetSuperclasses(model, "Logical View.Logical Model.com.ludet.hr.common.DomainObject");
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

    client = findClass(model, "Employee");
    supplier = findClass(model, "Manager");

    UMLDependency dep = model.createDependency(client, supplier, "reports to");
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
