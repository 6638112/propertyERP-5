package com.cnfantasia.server.ms.pd4ml.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zefer.pd4ml.PD4ProgressListener;

/**
 * pdf处理进度
 * 
 * @author liyulin
 * @version 1.0 2016年12月15日 下午1:52:53
 */
public final class ProgressMeter implements PD4ProgressListener {
	private Log logger = LogFactory.getLog(getClass());
	
	public void progressUpdate(int messageID, int progress, String note, long msec) {
		String tick = String.format("spend:%7d", msec);
		String progressString = String.format("%3d", progress);

		String step = "";
		switch (messageID) {
		case CONVERSION_BEGIN:
			step = "conversion begin";
			break;
		case HTML_PARSED:
			step = "html parsed";
			break;
		case DOC_TREE_BUILT:
			step = "document tree structure built";
			break;
		case HTML_LAYOUT_IN_PROGRESS:
			step = "layouting...";
			break;
		case HTML_LAYOUT_DONE:
			step = "layout done";
			break;
		case TOC_GENERATED:
			step = "TOC generated";
			break;
		case DOC_OUTPUT_IN_PROGRESS:
			step = "generating PDF...";
			break;
		case NEW_SRC_DOC_BEGIN:
			step = "proceed to new source document";
			break;
		case CONVERSION_END:
			step = "done.";
			break;
		}

		logger.debug(tick + " " + progressString + "% " + step + " " + note);;
	}
}