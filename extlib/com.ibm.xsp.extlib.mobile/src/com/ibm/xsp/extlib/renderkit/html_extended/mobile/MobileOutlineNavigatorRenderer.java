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






package com.ibm.xsp.extlib.renderkit.html_extended.mobile;

import javax.faces.context.FacesContext;

import com.ibm.xsp.extlib.component.outline.AbstractOutline;
import com.ibm.xsp.extlib.renderkit.html_extended.outline.AbstractOutlineRenderer;
import com.ibm.xsp.extlib.tree.ITreeRenderer;


public class MobileOutlineNavigatorRenderer extends AbstractOutlineRenderer{

    @Override   
    protected ITreeRenderer findTreeRenderer(FacesContext context, AbstractOutline outline) {
        ITreeRenderer r = new MobileNavigatorRenderer().findTreeRenderer(context, outline);
        return r;           
    }   
}
