/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.handler.impl;


import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.handler.XmiInOutHandler;

import java.util.HashMap;
import java.util.Map;

public abstract class DefaultXmiHandler implements XmiInOutHandler {

  protected Map<String, UMLModel> models = new HashMap<>();

  private byte[] clones = null;

  public void load(String filename) {
    doLoad(filename);

  }

  public void load(java.net.URI url) {
    models = new HashMap<>();

    doLoad(url);

  }

  protected abstract void doLoad(String filename);

  protected abstract void doLoad(java.net.URI uri);


}
