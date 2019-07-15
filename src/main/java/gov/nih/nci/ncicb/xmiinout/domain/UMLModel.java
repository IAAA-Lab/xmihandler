/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain;

import java.util.List;

/**
 * Reprensents a single model within an XMI file
 */
public interface UMLModel extends UMLTaggableElement {

  /**
   * @return the model name
   */
  String getName();

  /**
   * @return a List of packages belonging to this model
   */
  List<UMLPackage> getPackages();

  /**
   * Convenient method to retrieve one package by it's name.
   *
   * @param name the name of the package to find
   * @return The package with requested name or null if none exists
   */
  UMLPackage getPackage(String name);


  /**
   * a List of Classes belonging directly to the model, not under a package.
   */
  List<UMLClass> getClasses();

  /**
   * All generalizations in this model.
   */
  List<UMLGeneralization> getGeneralizations();

  /**
   * All dependencies in this model.
   */
  List<UMLDependency> getDependencies();

  /**
   * Add a dependency to the model. A dependency can be created with createDependency.
   * <br> Existing Dependency can not be added this way.
   *
   * @param dependency
   * @return
   */
  UMLDependency addDependency(UMLDependency dependency);

  /**
   * All associations in this model.
   */
  List<UMLAssociation> getAssociations();

  /**
   * @param client
   * @param supplier
   * @param name     Dependency's name
   * @return a non persisted UMLDependency. Use addDependency to persist.
   */
  UMLDependency createDependency(UMLDependencyEnd client, UMLDependencyEnd supplier, String name);

}
