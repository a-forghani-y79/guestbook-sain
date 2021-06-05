/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.guestbook.model.impl;

import com.liferay.docs.guestbook.model.GuestBookEntry;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GuestBookEntry in entity cache.
 *
 * @author liferay
 * @generated
 */
public class GuestBookEntryCacheModel
	implements CacheModel<GuestBookEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GuestBookEntryCacheModel)) {
			return false;
		}

		GuestBookEntryCacheModel guestBookEntryCacheModel =
			(GuestBookEntryCacheModel)object;

		if ((entryId == guestBookEntryCacheModel.entryId) &&
			(mvccVersion == guestBookEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, entryId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", entryId=");
		sb.append(entryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", email=");
		sb.append(email);
		sb.append(", message=");
		sb.append(message);
		sb.append(", guestbookId=");
		sb.append(guestbookId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusById=");
		sb.append(statusById);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GuestBookEntry toEntityModel() {
		GuestBookEntryImpl guestBookEntryImpl = new GuestBookEntryImpl();

		guestBookEntryImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			guestBookEntryImpl.setUuid("");
		}
		else {
			guestBookEntryImpl.setUuid(uuid);
		}

		guestBookEntryImpl.setEntryId(entryId);

		if (name == null) {
			guestBookEntryImpl.setName("");
		}
		else {
			guestBookEntryImpl.setName(name);
		}

		if (email == null) {
			guestBookEntryImpl.setEmail("");
		}
		else {
			guestBookEntryImpl.setEmail(email);
		}

		if (message == null) {
			guestBookEntryImpl.setMessage("");
		}
		else {
			guestBookEntryImpl.setMessage(message);
		}

		guestBookEntryImpl.setGuestbookId(guestbookId);
		guestBookEntryImpl.setGroupId(groupId);
		guestBookEntryImpl.setCompanyId(companyId);
		guestBookEntryImpl.setUserId(userId);

		if (userName == null) {
			guestBookEntryImpl.setUserName("");
		}
		else {
			guestBookEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			guestBookEntryImpl.setCreateDate(null);
		}
		else {
			guestBookEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			guestBookEntryImpl.setModifiedDate(null);
		}
		else {
			guestBookEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		guestBookEntryImpl.setStatus(status);
		guestBookEntryImpl.setStatusById(statusById);

		if (statusByUserName == null) {
			guestBookEntryImpl.setStatusByUserName("");
		}
		else {
			guestBookEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			guestBookEntryImpl.setStatusDate(null);
		}
		else {
			guestBookEntryImpl.setStatusDate(new Date(statusDate));
		}

		guestBookEntryImpl.resetOriginalValues();

		return guestBookEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		entryId = objectInput.readLong();
		name = objectInput.readUTF();
		email = objectInput.readUTF();
		message = objectInput.readUTF();

		guestbookId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		status = objectInput.readInt();

		statusById = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(entryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (message == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(message);
		}

		objectOutput.writeLong(guestbookId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusById);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public long mvccVersion;
	public String uuid;
	public long entryId;
	public String name;
	public String email;
	public String message;
	public long guestbookId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int status;
	public long statusById;
	public String statusByUserName;
	public long statusDate;

}