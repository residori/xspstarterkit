/*
 * � Copyright IBM Corp. 2009,2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package com.ibm.dots.event;

import com.ibm.dots.tasklet.events.DotsEventParams;

/**
 * @author dtaieb
 * 
 */
public class NSFHookNoteOpenEvent extends AbstractEMEvent {
	public static DotsEventParams[] params = { DotsEventParams.Username, DotsEventParams.SourceDbpath, DotsEventParams.Noteid,
			DotsEventParams.Flag };

	@Override
	public DotsEventParams[] getParams() {
		return params;
	}

	private String userName;
	private int updateFlag;

	/**
	 * @param eventId
	 */
	public NSFHookNoteOpenEvent(int eventId) {
		super(eventId);
	}

	/**
	 * 
	 */
	public NSFHookNoteOpenEvent() {
		super(IExtensionManagerEvent.HOOK_EVENT_NOTE_OPEN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.dots.event.AbstractEMEvent#parseEventBuffer(java.lang.String[])
	 */
	@Override
	protected boolean parseEventBuffer(String[] values) throws InvalidEventException {
		// sprintf( szParams, "%s,%s,%x,%x", UserName, szPathName, noteId, UpdateFlags );
		checkValues(values, 4);
		setUserName(values[0]);
		setDbPath(values[1]);
		setNoteId(values[2]);
		setUpdateFlag(parseInt(values[3]));
		return true;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	private void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the updateFlag
	 */
	public int getUpdateFlag() {
		return updateFlag;
	}

	/**
	 * @param updateFlag
	 *            the updateFlag to set
	 */
	private void setUpdateFlag(int updateFlag) {
		this.updateFlag = updateFlag;
	}

}
