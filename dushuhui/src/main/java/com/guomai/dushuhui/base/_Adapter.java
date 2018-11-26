package com.guomai.dushuhui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public abstract class _Adapter<T> extends BaseAdapter {
	protected int selectedIndex;
	protected List<T> list;
	protected Context context;

	public _Adapter(Context context, List<T> list) {
		this.context = context;
		this.list = list;
		this.selectedIndex = -1;
	}

	@Override
	public int getCount() {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	@Override
	public T getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void remove(T object) {
		list.remove(object);
	}

	public void setData(List<T> list)
	{
		this.list = list;
	}
	
	public void removeAt(int location) {
		list.remove(location);
	}

	/**
	 * 设置选中的项目
	 * 
	 * @param position
	 */
	public void setSelectedIndex(int position) {
		this.selectedIndex = position;
		notifyDataSetChanged();
	}

	/**
	 * need check null
	 * 
	 * @param listView
	 * @param position
	 * @return
	 */
	public View getItemView(ListView listView, int position) {
		int first = listView.getFirstVisiblePosition();
		return listView.getChildAt(position - first);
	}

	/**
	 * 设置选中，只改变背景色
	 * 
	 * @param listView
	 * @param position
	 */
	public void setSelected(ListView listView, int position) {
		int first = listView.getFirstVisiblePosition();
		View itemView = listView.getChildAt(selectedIndex - first);
		if (itemView != null) {
			// itemView.setBackgroundResource(R.color.full_transparent);
		}
		this.selectedIndex = position;
		itemView = listView.getChildAt(selectedIndex - first);
		if (itemView != null) {
			// itemView.setBackgroundResource(R.color.list_item_focused);
		}
	}

	/**
	 * 获得当前选中的索引
	 * 
	 * @return
	 */
	public int getSelectedIndex() {
		return this.selectedIndex;
	}

	/**
	 * 获得当前itemView
	 * 
	 * @param listview
	 * @return
	 */
	public ViewGroup getSelectedView(ListView listview) {
		return (ViewGroup) listview.getChildAt(selectedIndex - listview.getFirstVisiblePosition());
	}

	/**
	 * 将当前选中项移动到指定位置并选中
	 * 
	 * @param position
	 */
	public void moveSelection(int position) {
		if (position > -1 && position < list.size()) {
			T obj = list.get(selectedIndex);
			list.remove(selectedIndex);
			list.add(position, obj);
		}
		selectedIndex = position;
		notifyDataSetChanged();
	}

	public void addList(List<T> list) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	
	public void addFirst(List<T> list)
	{
		for(T t:list)
		{
			this.list.add(0, t);
		}
		notifyDataSetChanged();
	}
	
	public void addFirst(T entity)
	{
		this.list.add(0, entity);
		notifyDataSetChanged();
	}

	public List<T> getList() {
		return this.list;
	}

	/**
	 * 清空
	 */
	public void clear() {
		this.list.clear();
		notifyDataSetChanged();
	}

	public abstract View getView(int position, View convertView, ViewGroup parent);

	protected View createListItemView(int resource, ViewGroup root) {
		LayoutInflater inflater = LayoutInflater.from(context);
		return inflater.inflate(resource, root, false);
	}


}
