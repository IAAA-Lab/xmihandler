/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.handler;

import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;

import java.io.IOException;

public interface XmiInOutHandler {

  void load(String filename) throws XmiException, IOException;

  void load(java.net.URI url) throws XmiException, IOException;

  void save(String filename) throws XmiException, IOException;

  UMLModel getModel();

  UMLModel getModel(String modelName);

//  public UMLWriter getUMLWriter();


}
