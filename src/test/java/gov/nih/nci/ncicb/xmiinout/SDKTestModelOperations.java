package gov.nih.nci.ncicb.xmiinout;/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

import gov.nih.nci.ncicb.xmiinout.domain.UMLClass;
import gov.nih.nci.ncicb.xmiinout.domain.UMLInterface;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLOperation;
import gov.nih.nci.ncicb.xmiinout.handler.HandlerEnum;
import gov.nih.nci.ncicb.xmiinout.handler.XmiException;
import gov.nih.nci.ncicb.xmiinout.handler.XmiHandlerFactory;
import gov.nih.nci.ncicb.xmiinout.handler.XmiInOutHandler;
import gov.nih.nci.ncicb.xmiinout.util.ModelUtil;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SDKTestModelOperations {

  private static Logger logger = Logger.getLogger(TestArgo.class.getName());

  private XmiInOutHandler handler = null;

  private String filename = "sdk.uml";
  private String handlerEnumType = "ArgoUMLDefault";
  private String newFileExtension = ".new";
  private String modelName = "EA Model";

  private void init() {
    try {
      handler = XmiHandlerFactory.getXmiHandler(HandlerEnum.getHandlerEnumType(handlerEnumType));
      handler.load("src/test/resources/testdata/" + filename);
    } catch (XmiException | IOException e) {
      e.printStackTrace();
    }
  }


  private UMLModel getModel(String modelName) {
    UMLModel model;
    if (modelName == null)
      model = handler.getModel();
    else
      model = handler.getModel(modelName);

    System.out.println("Model: " + model.getName());
    return model;
  }

  private void loadModel(String f) {
    try {
      handler.load("build/" + f);
    } catch (XmiException | IOException e) {
      e.printStackTrace();
    }
  }

  private void saveModel() {
    try {
      handler.save("build/" + filename + newFileExtension);
    } catch (Exception e) {
      e.printStackTrace();
    } // end of try-catch
  }

  @Test
  public void testSuite() {

    init();

    //UMLModel model = getModel(modelName);
    UMLModel model = getModel(null);
    //gov.nih.nci.ncicb.xmiinout.Locator.printModel(model);

    testGetFullPackageName(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.Site");
    testSite(model);
    testTestOperation(model);

    saveModel();

    loadModel(filename + newFileExtension);
    //model = getModel(modelName);
    model = getModel(null);
    testGetFullPackageName(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.Site");
    testSite(model);
    testTestOperation(model);
    //gov.nih.nci.ncicb.xmiinout.Locator.printModel(model);


  }


  private void testSite(UMLModel model) {
    testFindClass(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.Site");
    testFindOperations(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.Site", false);
    assertOperation(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.Site", "setActivityStatus", false);
    assertOperation(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.Site", "validateStatus", false);
  }

  private void testTestOperation(UMLModel model) {
    testFindClass(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.TestOperationClass");
    testFindOperations(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.TestOperationClass", false);
    assertOperation(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.TestOperationClass", "testOperation1", false);
    assertOperation(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.TestOperationClass", "testOperation2", false);
    assertOperation(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.TestOperationClass", "testOperation3", false);
    assertOperation(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.TestOperationClass", "testOperation4", false);
    assertOperation(model, "Logical View.Logical Model.gov.nih.nci.cacoresdk.domain.operations.TestOperationClass", "testOperation5", false);
  }

  private void testGetFullPackageName(UMLModel model, String className) {
    String pkgName = className.substring(0, className.lastIndexOf("."));

    UMLClass clazz = ModelUtil.findClass(model, className);
    String result = ModelUtil.getFullPackageName(clazz);

    Assert.assertEquals("Testing GetFullPackageName - Found Wrong Package Name: " + result + "   For Class : " + className, pkgName, result);

    System.out.println("Testing GetFullPackageName - Correct Package: " + result);
  }

  private void testFindClass(UMLModel model, String fullClassName) {

    UMLClass clazz = ModelUtil.findClass(model, fullClassName);

    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);

    Assert.assertNotNull("Class not found -- " + fullClassName + " -- " + className, clazz);

    Assert.assertEquals("Class not found -- " + fullClassName + " -- " + className, clazz.getName(), className);

    System.out.println("Found Class: " + clazz.getName());

  }

  private void testFindOperations(UMLModel model, String fullClassName, boolean interfaze) {

    List<UMLOperation> operations;
    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);

    if (interfaze) {
      UMLInterface clazz = ModelUtil.findInterface(model, fullClassName);
      Assert.assertNotNull("Class not found -- " + fullClassName + " -- " + className, clazz);
      operations = clazz.getOperations();
    } else {
      UMLClass clazz = ModelUtil.findClass(model, fullClassName);
      Assert.assertNotNull("Class not found -- " + fullClassName + " -- " + className, clazz);
      operations = clazz.getOperations();
    }

    System.out.println("Operations for class: " + fullClassName);

    for (UMLOperation operation : operations) {
      System.out.println("Operation signature: " + ModelUtil.getOperationSignature(operation, true));
      System.out.println("Operation body: " + ModelUtil.getOperationBody(operation));
    }
  }

  private void assertOperation(UMLModel model, String fullClassName, String operationName, boolean interfaze) {

    List<UMLOperation> operations;
    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);

    if (interfaze) {
      UMLInterface clazz = ModelUtil.findInterface(model, fullClassName);
      Assert.assertNotNull("Class not found -- " + fullClassName + " -- " + className, clazz);
      operations = clazz.getOperations();
    } else {
      UMLClass clazz = ModelUtil.findClass(model, fullClassName);
      Assert.assertNotNull("Class not found -- " + fullClassName + " -- " + className, clazz);
      operations = clazz.getOperations();
    }

    System.out.println("Operations for class: " + fullClassName);
    boolean found = false;

    for (UMLOperation operation : operations) {
      if (operation.getName().equals(operationName)) {
        found = true;
        if (operation.getName().equals("setActivityStatus")) {
          String opSign = ModelUtil.getOperationSignature(operation, true);
          Assert.assertEquals("public void setActivityStatus(String status) throws Exception", opSign);
          String opBody = ModelUtil.getOperationBody(operation);
          Assert.assertEquals("activityStatus=status;\nvalidateStatus(activityStatus);", opBody);
        } else if (operation.getName().equals("validateStatus")) {
          String opSign = ModelUtil.getOperationSignature(operation, true);
          Assert.assertEquals("public void validateStatus(String status) throws Exception", opSign);
          String opBody = ModelUtil.getOperationBody(operation);
          Assert.assertEquals("if(status == null) throw new Exception(\"Status cannot be null\");\nelse if(status.length() == 0)  throw new Exception(\"Status cannot be empty\");", opBody);
        } else if (operation.getName().equals("testOperation1")) {
          String opSign = ModelUtil.getOperationSignature(operation, true);
          Assert.assertEquals("public abstract Address testOperation1()", opSign);
          String opBody = ModelUtil.getOperationBody(operation);
          Assert.assertNull(opBody);
        } else if (operation.getName().equals("testOperation2")) {
          String opSign = ModelUtil.getOperationSignature(operation, true);
          Assert.assertEquals("public void testOperation2(Address address)", opSign);
          String opBody = ModelUtil.getOperationBody(operation);
          Assert.assertEquals("return;", opBody);
        } else if (operation.getName().equals("testOperation3")) {
          String opSign = ModelUtil.getOperationSignature(operation, true);
          Assert.assertEquals("public String[] testOperation3()", opSign);
          String opBody = ModelUtil.getOperationBody(operation);
          //Assert.assertEquals("if(status == null) throw new Exception(\"Status cannot be null\");\nelse if(status.length() == 0)  throw new Exception(\"Status cannot be empty\");", opBody);
          Assert.assertEquals("return null;", opBody);
        } else if (operation.getName().equals("testOperation4")) {
          String opSign = ModelUtil.getOperationSignature(operation, true);
          Assert.assertEquals("public synchronized Address testOperation4(String value)", opSign);
          String opBody = ModelUtil.getOperationBody(operation);
          Assert.assertEquals("return null;", opBody);
        } else if (operation.getName().equals("testOperation5")) {
          String opSign = ModelUtil.getOperationSignature(operation, true);
          Assert.assertEquals("public final static synchronized void testOperation5(boolean value)", opSign);
          String opBody = ModelUtil.getOperationBody(operation);
          Assert.assertEquals("return;", opBody);

        }
      }

    }
    Assert.assertTrue("Operatation name: " + operationName + " cannot be found in class: " + fullClassName, found);
  }
}
