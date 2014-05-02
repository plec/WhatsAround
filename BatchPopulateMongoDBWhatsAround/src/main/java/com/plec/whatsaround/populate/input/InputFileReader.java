package com.plec.whatsaround.populate.input;

import java.util.List;

import com.plec.whatsaround.populate.bean.POI;

public interface InputFileReader {
	public List<POI> processInputFile(String fileName);
}
