package com.architter.widgets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.architter.connection.ConnectionManager;
import com.example.architter.ArchThisFragment;
import com.example.architter.IdeaViewFragment;
import com.example.architter.MyFragment;
import com.architter.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class IdeasScroll extends RelativeLayout implements ScrollViewListener, OnClickListener {
	ObservableScrollView scroll;
	String tags = "";
	String search = "";
	int idUser = 0;
	int page = 1;
	private boolean loading = false;
	private MyFragment fragment;
	private int resource = 0;
	public IdeasScroll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public IdeasScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public IdeasScroll(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		inflate(getContext(), R.layout.two_column_scroll, this);
		init();
	}

	private void init() {
		RelativeLayout loading = (RelativeLayout) this.findViewById(R.id.loadingView);
		AnimatedView loading_gif = new AnimatedView(loading.getContext());
		loading_gif.loadDrawable(R.drawable.loading_architter);
		loading.addView(loading_gif);
		findViewById(R.id.notFound).setVisibility(GONE);

		scroll = (ObservableScrollView) this.findViewById(R.id.ideasScroll);
		scroll.setScrollViewListener(this);
	}

	private void clean() {
		this.tags = "";
		this.search = "";
	}

	public void loadIdeas(String tags) {
		clean();
		this.tags = tags;
		loadIdeas();
	}
	public void searchIdeas(String search) {
		clean();
		this.search = search;
		loadIdeas();
	}
	public void loadUserIdeas(String tags) {
		clean();
		this.tags = tags;
		loadUserIdeas();
	}
	public void searchUserIdeas(String search) {
		clean();
		this.search = search;
		loadUserIdeas();
	}
	public void loadTagsIdeas(String tags) {
		clean();
		this.tags = tags;
		loadTagsIdeas();
	}
	public void searchTagsIdeas(String search) {
		clean();
		this.search = search;
		loadTagsIdeas();
	}

	public void loadIdeas(String tags, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
			this.findViewById(R.id.loadingView).setVisibility(VISIBLE);
		}
		loadIdeas(tags);
	}
	public void searchIdeas(String search, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
			this.findViewById(R.id.loadingView).setVisibility(VISIBLE);
		}
		searchIdeas(search);
	}
	public void loadUserIdeas(String tags, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
			this.findViewById(R.id.loadingView).setVisibility(VISIBLE);
		}
		loadUserIdeas(tags);
	}
	public void searchUserIdeas(String search, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
			this.findViewById(R.id.loadingView).setVisibility(VISIBLE);
		}
		searchUserIdeas(search);
	}
	public void loadTagsIdeas(String tags, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
			this.findViewById(R.id.loadingView).setVisibility(VISIBLE);
		}
		loadTagsIdeas(tags);
	}
	public void searchTagsIdeas(String search, boolean erase) {
		if(erase) {
			LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
			LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
			column1.removeAllViews();
			column2.removeAllViews();
			page = 1;
			this.findViewById(R.id.loadingView).setVisibility(VISIBLE);
		}
		searchTagsIdeas(search);
	}


	public void loadIdeas() {
		resource = 0;
		RequestParams params = new RequestParams();
		params.put("page", ""+page);
		params.put("tags", tags);
		params.put("search", search);
		findViewById(R.id.notFound).setVisibility(GONE);
		if(!loading) {
			loading = true;
			ConnectionManager.getIdeas(params, new  JsonHttpResponseHandler() {
				@Override
				public void onFailure(Throwable arg0) {
					Toast.makeText(getContext(), "Network error, please try again later.",Toast.LENGTH_LONG).show();
					loading = false;
				}
				@Override
				public void onSuccess(JSONArray ideas) {
					findViewById(R.id.loadingView).setVisibility(GONE);
					loadElements(ideas);
					loading = false;
				}
			});
		}
	}

	public void loadUserIdeas() {
		resource = 1;
		RequestParams params = new RequestParams();
		params.put("page", ""+page);
		params.put("tags", tags);
		params.put("search", search);
		findViewById(R.id.notFound).setVisibility(GONE);
		if(!loading){ 
		loading = true;
			ConnectionManager.getUserIdeas(params, new  JsonHttpResponseHandler() {
				@Override
				public void onFailure(Throwable arg0) {
					Toast.makeText(getContext(), "Network error, please try again later.",Toast.LENGTH_LONG).show();
					loading = false;
				}
				@Override
				public void onSuccess(JSONArray ideas) {
					findViewById(R.id.loadingView).setVisibility(GONE);
					loadElements(ideas);
					loading = false;
				}
			});
		}
	}
	public void loadTagsIdeas() {
		resource = 2;
		RequestParams params = new RequestParams();
		params.put("page", ""+page);
		params.put("tags", tags);
		params.put("search", search);
		findViewById(R.id.notFound).setVisibility(GONE);
		if(!loading){ 
			loading = true;
			ConnectionManager.getTagsIdeas(params, new  JsonHttpResponseHandler() {
				@Override
				public void onFailure(Throwable arg0) {
					Toast.makeText(getContext(), "Network error, please try again later.",Toast.LENGTH_LONG).show();
					loading = false;
				}
				@Override
				public void onSuccess(JSONArray ideas) {
					findViewById(R.id.loadingView).setVisibility(GONE);
					loading = false;
					loadElements(ideas);
				}
			});
		}
	}


	public void loadElements(JSONArray ideas){
		LinearLayout column1 = (LinearLayout) this.findViewById(R.id.linear2);
		LinearLayout column2 = (LinearLayout) this.findViewById(R.id.linear3);
		IdeaWidget idea = null;
		IdeaWidget cont = new IdeaWidget(getContext());
		int column = 1;
		if(ideas.length() == 0) {
			this.findViewById(R.id.notFound).setVisibility(VISIBLE);
		} else {
			this.findViewById(R.id.notFound).setVisibility(GONE);
			page++;
			for (int i = 0; i < ideas.length(); i++) {
				try {
					JSONObject invention = ideas.getJSONObject(i);
					String img = invention.getString("img");
					LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					idea = (IdeaWidget) inflater.inflate(R.layout.idea_component, cont, false);
					img = ConnectionManager.getImgUrl(img);
					String username = invention.getString("username");
					String avatar = invention.getString("avatar");
					int idea_id = invention.getInt("id");
					boolean user_has = invention.getBoolean("user_has");
					idea.setImage(img);
					idea.setUsername(username);
					idea.setUserPicture(avatar);
					idea.setListener(this);
					idea.setIdeaId(idea_id);
					idea.userHas(user_has);
					switch (column) {
					case 1:
						column1.addView(idea);
						column++;
						break;
					case 2:
						column2.addView(idea);
						column++;
					default:
						column = 1;
						break;
					};
				} catch (JSONException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}

	public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
			int oldx, int oldy) {
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear1);
	    if(linearLayout.getMeasuredHeight() <= scrollView.getScrollY() +
	           scrollView.getHeight()) {
	        switch(resource) {
	        	case 0:
	        		loadIdeas();
	        		break;
	        	case 1:
	        		loadUserIdeas();
	        		break;
	        	case 2:
	        		loadTagsIdeas();
	        		break;
	        }
	    }
	    else {
	        //do nothing
	    }
	}

	public void setFragment(MyFragment fragment) {
		this.fragment = fragment;
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.image: {
				IdeaWidget i = (IdeaWidget) v.getParent();
				int id = i.getIdeaId();
				IdeaViewFragment newFragment = new IdeaViewFragment();
				newFragment.setIdeaId(id);
				fragment.loadFragment(newFragment);
				break;
			}
			case R.id.archthis: {
				IdeaWidget i = (IdeaWidget) v.getParent().getParent();
				int id = i.getIdeaId();
				ArchThisFragment newFragment = new ArchThisFragment();
				newFragment.setIdeaId(id);
				fragment.loadFragment(newFragment);
				break;
			}
			default: {
				break;
			}
		}
	}
}
