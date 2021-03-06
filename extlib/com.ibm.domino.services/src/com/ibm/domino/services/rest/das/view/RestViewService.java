/* ***************************************************************** */
/* Licensed Materials - Property of IBM                              */
/*                                                                   */
/* Copyright IBM Corp. 1985, 2013 All Rights Reserved                */
/*                                                                   */
/* US Government Users Restricted Rights - Use, duplication or       */
/* disclosure restricted by GSA ADP Schedule Contract with           */
/* IBM Corp.                                                         */
/*                                                                   */
/* ***************************************************************** */






package com.ibm.domino.services.rest.das.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.View;

import com.ibm.commons.Platform;
import com.ibm.commons.util.StringUtil;
import com.ibm.domino.services.ServiceException;
import com.ibm.domino.services.rest.das.RestDominoService;


/**
 * Domino View Service.
 */
public abstract class RestViewService extends RestDominoService {

    private ViewParameters parameters;

    // Work members
    protected View defaultView;
    protected boolean shouldRecycleView;
    protected View view;
        
    protected RestViewService(HttpServletRequest httpRequest, HttpServletResponse httpResponse, ViewParameters parameters) {
        super(httpRequest, httpResponse);
        this.parameters = wrapViewParameters(parameters);
    }

    protected ViewParameters wrapViewParameters(ViewParameters parameters) {
        return parameters;
    }
    
    @Override
    public ViewParameters getParameters() {
        return parameters;
    }
    
    @Override
    public void recycle() {
        if(view!=null && shouldRecycleView) {
            try {
                view.recycle();
                view = null;
            } catch(NotesException ex) {
                Platform.getInstance().log(ex);
            }
        }
        super.recycle();
    }
    
    @Override
    public abstract void renderService() throws ServiceException;

    // Access to the backend classes
    public View getView() throws NotesException {
        if(view==null) {
            loadView();
        }
        return view;
    }
    
    public void setDefaultView(View defaultView) {
        this.defaultView = defaultView;     
    }
    
    protected void loadView() throws NotesException {
        ViewParameters parameters = getParameters();
        Database db = getDatabase(parameters);
        String viewName = parameters.getViewName();
        if(StringUtil.isEmpty(viewName)) {
            if(defaultView==null) {
                throw new IllegalStateException(com.ibm.domino.services.ResourceHandler.getSpecialAudienceString("RestViewService.Nodefaultviewassignedtotheservice")); // $NLX-RestViewService.Nodefaultviewassignedtotheservice-1$
            }
            this.view = defaultView;
            this.view.setAutoUpdate(false);
            this.shouldRecycleView = false;
            return;
        }
        this.view = db.getView(viewName);
        if(view==null) {
            throw new NotesException(0,StringUtil.format(com.ibm.domino.services.ResourceHandler.getSpecialAudienceString("RestViewService.Unknownview0indatabase1"),viewName,db.getFileName())); // $NLX-RestViewService.Unknownview0indatabase1-1$
        }
        this.view.setAutoUpdate(false);
        this.shouldRecycleView = true;
    }
    
    /**
     * Calculate the view entry id.
     */
    public String getEntryId(RestViewNavigator nav) throws ServiceException {
        return nav.getPosition()+"-"+nav.getUniversalId(); // $NON-NLS-1$
    }
    public String getEntryUNID(String id) {
        if(StringUtil.isNotEmpty(id)) {
            // if this id is composed by position+unid, extract the unid
            int pos = id.indexOf('-');
            if(pos>0) {
                return id.substring(pos+1);
            }
            // Else, this is just the unid
            return id;
        }
        return null;
    }
    
    /**
     * Called when a new document is being created in the database.
     * 
     * @return true to continue
     */
    public boolean queryNewDocument() {
        return true;
    }
    
    /**
     * Called when a document is being opened for update purposes.
     * 
     * @return true to continue
     */
    public boolean queryOpenDocument(String id) {
        return true;
    }

    /**
     * Called right before the document is being saved (after computeWithForm).
     * 
     * @return true to continue
     */
    public boolean querySaveDocument(Document doc) {
        return true;
    }
    
    /**
     * Called right before a document is about to be deleted.
     * 
     * @return true to continue
     */
    public boolean queryDeleteDocument(String id) {
        return true;
    }

    /**
     * Called after a new document is created in memory.
     */
    public void postNewDocument(Document doc) {
        
    }

    /**
     * Called after a document is opened in memory.
     */
    public void postOpenDocument(Document doc)  {
        
    }
    
    /**
     * Called after a document had been saved.
     */
    public void postSaveDocument(Document doc)  {
        
    }   
    
    /**
     * Called after a document had been deleted.
     */
    public void postDeleteDocument(String id) {
        
    }
    
}
