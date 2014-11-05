package com.lin.clould.framework.common.view;

public class View {

	private String nextPage;
	private boolean redirect;

	public View(String nextPage) {
		this(nextPage, false);
	}

	public View(String nextPage, boolean redirect) {
		this.nextPage = nextPage;
		this.redirect = redirect;
	}

	public String getNextPage() {
		return nextPage;
	}

	public boolean isRedirect() {
		return redirect;
	}

}
