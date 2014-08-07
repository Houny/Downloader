package com.xckevin.download.ui;

import com.xckevin.download.DownloadTask;
import com.xckevin.download.DownloadTaskIDCreator;

public class IDCreate implements DownloadTaskIDCreator {

	@Override
	public String createId(DownloadTask task) {
		// TODO Auto-generated method stub
		return task.getId();
	}

}
