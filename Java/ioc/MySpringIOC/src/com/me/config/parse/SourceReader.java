package com.me.config.parse;

import java.util.Map;

import com.me.config.Bean;

public interface SourceReader {
	Map<String, Bean> getConfig(String path);
}
