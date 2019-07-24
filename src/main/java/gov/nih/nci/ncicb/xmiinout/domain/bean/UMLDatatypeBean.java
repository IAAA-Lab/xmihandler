/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/xmihandler/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.xmiinout.domain.bean;

import gov.nih.nci.ncicb.xmiinout.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UMLDatatypeBean extends JDomDomainObject implements UMLDatatype {

  private String name;

  public UMLDatatypeBean(org.jdom.Element element, String name) {
    super(element);
    this.name = name;
  }

  @Override
  public List<UMLAttribute> getAttributes() {
    return Collections.emptyList();
  }

  @Override
  public UMLOperation getOperation(String name) {
    return null;
  }

  @Override
  public List<UMLOperation> getOperations() {
    return Collections.emptyList();
  }

  @Override
  public UMLAttribute getAttribute(String name) {
    return null;
  }

  public String getName() {
    return name;
  }

  @Override
  public Set<UMLAssociation> getAssociations() {
    return null;
  }

  @Override
  public List<UMLGeneralization> getGeneralizations() {
    return Collections.emptyList();
  }
}
