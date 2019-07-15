/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

import java.util.Collection;

public interface UMLPackage extends UMLTaggableElement {

  /**
   * The package name.
   */
  String getName();

  /**
   * @return the parent package or null if this is the root package
   */
  UMLPackage getParent();

  /**
   * All packages under this package
   */
  Collection<UMLPackage> getPackages();

  /**
   * Convenient method to retrieve one package by it's name.
   *
   * @param name the name of the package to find
   * @return The package with requested name or null if none exists
   */
  UMLPackage getPackage(String name);

  /**
   * All classes directly under this package
   */
  Collection<UMLClass> getClasses();

  /**
   * All interfaces directly under this package
   */
  Collection<UMLInterface> getInterfaces();

  /**
   * Convenient method to retrieve one Class by it's name.
   *
   * @param name the name of the class to find
   * @return The class with requested name or null if none exists
   */
  UMLClass getClass(String name);

  /**
   * Convenient method to retrieve one Interface by it's name.
   *
   * @param name the name of the class to find
   * @return The Interface with requested name or null if none exists
   */
  UMLInterface getInterface(String name);

}
