package gov.nih.nci.ncicb.xmiinout;/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

import gov.nih.nci.ncicb.xmiinout.domain.UMLClass;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLPackage;


public class Locator {

  public static UMLClass findClass(UMLModel model, String className) {
    for (UMLPackage pkg : model.getPackages()) {
      UMLClass c = findClass(pkg, className);
      if (c != null)
        return c;
    }
    return null;
  }

  public static UMLClass findClass(UMLPackage pkg, String className) {
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


}
