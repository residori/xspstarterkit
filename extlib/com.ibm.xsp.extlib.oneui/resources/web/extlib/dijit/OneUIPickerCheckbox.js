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





dojo.provide("extlib.dijit.OneUIPickerCheckbox");

dojo.require("extlib.dijit.OneUIDialog")
dojo.require("extlib.dijit.PickerCheckbox")

dojo.declare(
	'extlib.dijit.OneUIPickerCheckbox',
	[extlib.dijit.PickerCheckbox],
	{
        listWidth: "100%",
		templateString: dojo.cache("extlib.dijit", "templates/OneUIPickerCheckbox.html")
	}
);
