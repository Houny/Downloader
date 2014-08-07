package com.xckevin.download.ui;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xckevin.download.DownloadListener;
import com.xckevin.download.DownloadManager;
import com.xckevin.download.DownloadTask;
import com.xckevin.download.R;
import com.xckevin.download.model.GameInfoModel;

public class ListAdapter extends BaseAdapter {
	private List<DownloadTask> list;
	private Context context;
	private DownloadListener listener;
	private DownloadManager downloadManager;

	public ListAdapter(List<DownloadTask> list, DownloadListener listener,
			DownloadManager downloadManager, Context context) {
		super();
		this.downloadManager = downloadManager;
		this.listener = listener;
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size() > 0 ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return list.size() > 0 ? list.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return list.size() > 0 ? position : 0;
	}

	public void removeItem(DownloadTask task){
		try {
			list.remove(task);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void addItem(DownloadTask task){
		try {
			list.add(task);
			downloadManager.addDownloadTask(task, listener);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final DownloadTask task = list.get(position);
		downloadManager.updateDownloadTaskListener(task, listener);
		ViewHolder holder = null;
		if (convertView == null) {

			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_download, null);

		}

		if (holder == null) {
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.item_name);
			holder.name.setTag(task.getUrl() + "name");
			holder.speend = (TextView) convertView
					.findViewById(R.id.item_speed);
			holder.speend.setTag(task.getUrl() + "speed");
			holder.start = (Button) convertView.findViewById(R.id.item_start);
			holder.start.setTag(task.getUrl() + "start");
			holder.stop = (Button) convertView.findViewById(R.id.item_stop);
			holder.stop.setTag(task.getUrl() + "stop");
			holder.progress = (ProgressBar) convertView
					.findViewById(R.id.item_progress);
			holder.progress.setTag(task.getUrl());
		}

		holder.name.setText(task.getName());

		Log.e("Task!=null", task.getStatus() + "");
			if (task.getDownloadFinishedSize() == task.getDownloadTotalSize()) {

				holder.start.setText("Install");
				holder.stop.setText("Delete");

			}else{
				int progress = (int) ((task.getDownloadFinishedSize() * 100) / task
						.getDownloadTotalSize());
				holder.progress.setProgress(progress);
				holder.speend.setText(" KB/S  " + task.getDownloadFinishedSize()
						+ "/" + task.getDownloadTotalSize());
				
				if(task.getDownloadFinishedSize()!=0&&task.getDownloadFinishedSize()<task.getDownloadTotalSize()){
					holder.start.setText("Resume");
				}
			}
			
			
		

		holder.start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			
					switch (task.getStatus()) {
					case DownloadTask.STATUS_CANCELED:
						downloadManager.addDownloadTask(task, listener);
						break;
					case DownloadTask.STATUS_PAUSED:
						downloadManager.resumeDownload(task,listener);
						
						break;
					case DownloadTask.STATUS_FINISHED:
						Toast.makeText(context,
								"已下载完成" + task.getDownloadSavePath(),
								Toast.LENGTH_SHORT).show();
						break;
					case DownloadTask.STATUS_RUNNING:
						downloadManager.pauseDownload(task,listener);
						break;
					default:
						break;
					}

				

			}
		});

		holder.stop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					downloadManager.cancelDownload(task,listener);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		return convertView;
	}

	private class ViewHolder {
		TextView name;
		TextView speend;
		ProgressBar progress;
		Button start;
		Button stop;
	}

}
