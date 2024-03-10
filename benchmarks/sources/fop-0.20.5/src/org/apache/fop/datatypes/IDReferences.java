/*
 * $Id: IDReferences.java,v 1.14.2.5 2003/02/25 10:48:28 jeremias Exp $
 * ============================================================================
 *                    The Apache Software License, Version 1.1
 * ============================================================================
 * 
 * Copyright (C) 1999-2003 The Apache Software Foundation. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modifica-
 * tion, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include the following acknowledgment: "This product includes software
 *    developed by the Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself, if
 *    and wherever such third-party acknowledgments normally appear.
 * 
 * 4. The names "FOP" and "Apache Software Foundation" must not be used to
 *    endorse or promote products derived from this software without prior
 *    written permission. For written permission, please contact
 *    apache@apache.org.
 * 
 * 5. Products derived from this software may not be called "Apache", nor may
 *    "Apache" appear in their name, without prior written permission of the
 *    Apache Software Foundation.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * APACHE SOFTWARE FOUNDATION OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLU-
 * DING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ============================================================================
 * 
 * This software consists of voluntary contributions made by many individuals
 * on behalf of the Apache Software Foundation and was originally created by
 * James Tauber <jtauber@jtauber.com>. For more information on the Apache
 * Software Foundation, please see <http://www.apache.org/>.
 */ 
package org.apache.fop.datatypes;

import org.apache.fop.pdf.PDFGoTo;
import org.apache.fop.layout.Area;
import org.apache.fop.apps.FOPException;

// Java
import java.util.HashMap;
import java.util.Iterator;

/**
  IDReferences contains a map of IDs and the objects to which
  they refer. It also contains a list of references to IDs which
  have yet to be encountered.

  Modified by Mark Lillywhite mark-fop@inomial.com. Added
  getInvalidElements() so that StreamRenderer can tell what
  hasn't been determined yet.

  Modified by lmckenzi@ca.ibm.com
  Sometimes IDs are created, but not validated. This code fixes
  the incorrect complaint that the ID already exists which prevents
  basic-links from working (sometimes).

  */
public class IDReferences {
    private HashMap idReferences, idValidation, idUnvalidated;

    static final int ID_PADDING = 5000;    // space to add before id y position

    /**
     * Constructor for IDReferences
     */
    public IDReferences() {
        idReferences = new HashMap();
        idValidation = new HashMap();
        idUnvalidated = new HashMap();
    }


    /**
     * Creates and configures the specified id.
     *
     * @param id     The id to initialize
     * @param area   The area where this id was encountered
     * @exception FOPException
     */
    public void initializeID(String id, Area area) throws FOPException {
        createID(id);
        configureID(id, area);
    }


    /**
     * Creates id entry
     *
     * @param id     The id to create
     * @param area   The area where this id was encountered
     * @exception FOPException
     */
    public void createID(String id) throws FOPException {
        if (id != null &&!id.equals("")) {
            if (doesUnvalidatedIDExist(id)) {
                removeFromUnvalidatedIDList(id);
                //Steve's (gears@apache.org) comment: Is this right?
                removeFromIdValidationList(id);
            }
            else if (doesIDExist(id)) {
                throw new FOPException("The id \"" + id
                                       + "\" already exists in this document");
            } else {
                createNewId(id);
                removeFromIdValidationList(id);
            }

        }
    }

    /**
     * Creates id entry that hasn't been validated
     *
     * @param id     The id to create
     */
    public void createUnvalidatedID(String id) {
        if (id != null &&!id.equals("")) {
            if (!doesIDExist(id)) {
                createNewId(id);
                addToUnvalidatedIdList(id);
            }
        }
    }

    /**
     * Adds created id list of unvalidated ids that have already
     * been created. This should be used if it is unsure whether
     * the id is valid but it must be anyhow.
     *
     * @param id     The id to create
     */
    public void addToUnvalidatedIdList(String id) {
        idUnvalidated.put(id,"");
    }

    /**
     * Removes id from list of unvalidated ids.
     * This should be used if the id has been determined
     * to be valid.
     *
     * @param id     The id to remove
     */
    public void removeFromUnvalidatedIDList(String id) {
        idUnvalidated.remove(id);
    }

    /**
     * Determines whether specified id already exists in
     * idUnvalidated
     *
     * @param id     The id to search for
     * @return true if ID was found, false otherwise
     */
    public boolean doesUnvalidatedIDExist(String id) {
        return idUnvalidated.containsKey(id);
    }

    /**
     * Configures this id
     *
     * @param id     The id to configure
     * @param area   The area where the id was encountered
     */
    public void configureID(String id, Area area) {
        if (id != null && !id.equals("")) {
            setPosition(id,
                        area.getPage().getBody().getXPosition()
                        + area.getTableCellXOffset() - ID_PADDING,
                        area.getPage().getBody().getYPosition()
                        - area.getAbsoluteHeight() + ID_PADDING);
            setPageNumber(id, area.getPage().getFormattedNumber());
            area.getPage().addToIDList(id);
        }
    }

    /**
     * Adds id to validation list to be validated.  This should be
     * used if it is unsure whether the id is valid.
     *
     * @param id id to be added
     */
    public void addToIdValidationList(String id) {
        idValidation.put(id, "");
    }



    /**
     * Removes id from validation list. This should be used if the id has been determined to be valid
     *
     * @param id     the id to remove
     */
    public void removeFromIdValidationList(String id) {
        idValidation.remove(id);
    }


    /**
     * Removes id from IDReferences
     *
     * @param id     The id to remove
     */
    public void removeID(String id) {
        idReferences.remove(id);
    }


    /**
     * Determines whether all id's are valid
     *
     * @return true if all id's are valid, false otherwise
     */
    public boolean isEveryIdValid() {
        return (idValidation.size() == 0);
    }



    /**
     * Returns all invalid id's still remaining in the validation list
     *
     * @return invalid ids from validation list
     */
    public String getInvalidIds() {
        StringBuffer list = new StringBuffer();
        Iterator iterator = idValidation.keySet().iterator();
        while (iterator.hasNext()) {
            list.append("\n\"").append(iterator.next().toString()).append("\" ");
        }
        return list.toString();
    }


    /**
     * Determines whether specified id already exists in IDReferences
     *
     * @param id     the id to search for
     * @return true if ID was found, false otherwise
     */
    public boolean doesIDExist(String id) {
        return idReferences.containsKey(id);
    }


    /**
     * Determines whether the GoTo reference for the specified id is defined
     *
     * @param id     the id to search for
     * @return true if GoTo reference is defined, false otherwise
     */
    public boolean doesGoToReferenceExist(String id) {
        IDNode node = (IDNode)idReferences.get(id);
        return node.isThereInternalLinkGoTo();
    }




    /**
     * Returns the reference to the GoTo object used for the internal link
     *
     * @param id     the id whose reference to use
     * @return reference to GoTo object
     */
    public String getInternalLinkGoToReference(String id) {
        IDNode node = (IDNode)idReferences.get(id);
        return node.getInternalLinkGoToReference();
    }



    /**
     * creates an Internal Link GoTo object for this id
     *
     * @param id     The id for which to set the Internal Link Go To
     * @param objectNumber
     * The object number to use for the GoTo object
     * @return the object reference of the new GoTo object
     */
    public String createInternalLinkGoTo(String id, int objectNumber) {
        IDNode node = (IDNode)idReferences.get(id);    // retrieve id node
        node.createInternalLinkGoTo(objectNumber);    // create Internal Link GoTo object
        return node.getInternalLinkGoToReference();    // return Internal Link Go To object reference
    }



    /**
     * Adds an id to IDReferences
     *
     * @param id     the id to add
     */
    public void createNewId(String id) {
        IDNode node = new IDNode(id);
        idReferences.put(id, node);
    }


    /**
     * Returns the PDFGoTo object for the specified id
     *
     * @param id     the id for which the PDFGoTo to be retrieved is associated
     * @return the PDFGoTo object associated with the specified id
     */
    public PDFGoTo getPDFGoTo(String id) {
        IDNode node = (IDNode)idReferences.get(id);
        return node.getInternalLinkGoTo();
    }


    /**
     * sets the page reference for the internal link's GoTo.  The GoTo will jump to this page reference.
     *
     * @param pageReference
     * the page reference to which the internal link GoTo should jump
     * ex. 23 0 R
     */
    public void setInternalGoToPageReference(String id,
            String pageReference) {
        IDNode node = (IDNode)idReferences.get(id);
        if (node != null) {
            node.setInternalLinkGoToPageReference(pageReference);
        }
    }


    /**
     * Sets the page number for the specified id
     *
     * @param id     The id whose page number is being set
     * @param pageNumber The page number of the specified id
     */
    public void setPageNumber(String id, String pageNumber) {
        IDNode node = (IDNode)idReferences.get(id);
        node.setPageNumber(pageNumber);
    }


    /**
     * Returns the page number where the specified id is found
     *
     * @param id     The id whose page number to return
     * @return the page number of the id, or null if the id does not exist
     */
    public String getPageNumber(String id) {
        if (doesIDExist(id)) {
            IDNode node = (IDNode)idReferences.get(id);
            return node.getPageNumber();
        } else {
            addToIdValidationList(id);
            return null;
        }
    }


    /**
     * Sets the x and y position of specified id
     *
     * @param id     the id whose position is to be set
     * @param x      x position of id
     * @param y      y position of id
     */
    public void setPosition(String id, int x, int y) {
        IDNode node = (IDNode)idReferences.get(id);
        node.setPosition(x, y);
    }

    public Iterator getInvalidElements() {
        return idValidation.keySet().iterator();
    }
    
    /**
     * Returns a destination reference for the node with the
     * specified id.  If id does not exist, returns null.
     * Destination format is: [ ?objectId 0 R /XYZ ?x ?y null ]
     *
     * @param id The id whose destination reference to return
     * @return Destination reference for this node
     */
    public String getDestinationRef(String id) {
        if (doesIDExist(id)) {
            IDNode node = (IDNode)idReferences.get(id);
            return "[ " + node.getPageReference() + " /XYZ " +
            node.getXPosition()/1000f + " " + node.getYPosition()/1000f + " null ]";
        } else {
            addToIdValidationList(id);
            return null;
        }
    }
    
}
