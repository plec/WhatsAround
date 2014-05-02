package com.plec.whatsaround.populate;

import com.plec.whatsaround.populate.mongodb.MongoDBPOIDao;

public class MainSearch {
	public static void main(String[] args) {
		MainSearch main = new MainSearch();
		main.execute();
	}
	private void execute() {
		MongoDBPOIDao dao = new MongoDBPOIDao();
		dao.search(47.333, 5.033, 100);
	}
}
